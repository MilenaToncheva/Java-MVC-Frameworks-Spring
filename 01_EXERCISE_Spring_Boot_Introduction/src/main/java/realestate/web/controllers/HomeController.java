package realestate.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import realestate.domain.models.view.OfferViewModel;
import realestate.service.OfferService;
import realestate.util.HtmlReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final OfferService offerService;
    private final ModelMapper modelMapper;
    private final HtmlReader htmlReader;
    private final static String HTML_FILE_PATH = "C:\\Users\\anato\\OneDrive\\Documents\\SOFTUNI\\Java\\Java MVC Framework-Spring\\February 2019\\Spring introduction\\src\\main\\resources\\static\\index.html";

    @Autowired
    public HomeController(OfferService offerService, ModelMapper modelMapper, HtmlReader htmlReader) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.htmlReader = htmlReader;
    }

    @GetMapping("/")
    @ResponseBody
    public String index() throws IOException {
        return this.prepareView();
    }



    private String prepareView() throws IOException {
        List<OfferViewModel> offers = Arrays.stream(this.modelMapper.map(this.offerService.findAllOffers(), OfferViewModel[].class))
                .collect(Collectors.toList());
        StringBuilder offersHtml = new StringBuilder();
        if (offers.size() == 0) {
            offersHtml.append("<div class=\"apartment\" style=\"border: red solid 1px\">")
                    .append("There are't any offers!")
                    .append("</div>");
        } else {
            for (OfferViewModel offer : offers) {
                offersHtml.append("<div class=\"apartment\">")
                        .append(String.format("<p>Rent: %s</p>", offer.getApartmentRent())).append(System.lineSeparator())
                        .append(String.format("<p>Type: %s</p>", offer.getApartmentType())).append(System.lineSeparator())
                        .append(String.format("<p>Commission: %s</p>", offer.getAgencyCommission())).append(System.lineSeparator())
                        .append("</div>").append(System.lineSeparator());
            }
        }
        return this.htmlReader.readHtmlFile(HTML_FILE_PATH).replace("{{offers}}", offersHtml.toString().trim());

    }

}


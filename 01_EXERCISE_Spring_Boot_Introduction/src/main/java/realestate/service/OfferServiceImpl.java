package realestate.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realestate.domain.entities.Offer;
import realestate.domain.models.service.OfferFindServiceModel;
import realestate.domain.models.service.OfferServiceModel;
import realestate.repository.OfferRepository;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
 public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, Validator validator, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerOffer(OfferServiceModel offerServiceModel) {
        if (this.validator.validate(offerServiceModel).size() != 0) {
            throw new IllegalArgumentException("Something went wrong!");
        }
        this.offerRepository.saveAndFlush(this.modelMapper.map(offerServiceModel, Offer.class));
    }

    @Override
    public List<OfferServiceModel> findAllOffers() {
        return Arrays.stream(this.modelMapper.map(this.offerRepository.findAll(), OfferServiceModel[].class)).collect(Collectors.toList());
    }

    @Override
    public void findOffer(OfferFindServiceModel offerFindServiceModel) {
        if(this.validator.validate(offerFindServiceModel).size()!=0){
            throw new IllegalArgumentException("Something went wrong!");

        }

        Offer offer=this.offerRepository.findAll()
                .stream()
                .filter(o->offerFindServiceModel.getFamilyApartmentType().toLowerCase().equals(o.getApartmentType().toLowerCase())
                        &&
                        offerFindServiceModel.getFamilyBudget().compareTo(o.getApartmentRent()
                                .multiply(new BigDecimal(1).add(o.getAgencyCommission().divide(new BigDecimal(100)))))>=1)
                .findFirst()
                .orElse(null);

        if(offer==null){
            throw new IllegalArgumentException("There is no offer meeting the requirements of the family!");
        }
        this.offerRepository.delete(offer);
    }
}

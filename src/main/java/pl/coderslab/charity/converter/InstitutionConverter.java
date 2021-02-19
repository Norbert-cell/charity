package pl.coderslab.charity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

@Component
public class InstitutionConverter implements Converter<String, Institution> {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public Institution convert(String s) {
        return institutionRepository.findById(Long.parseLong(s)).orElse(new Institution());
    }
}

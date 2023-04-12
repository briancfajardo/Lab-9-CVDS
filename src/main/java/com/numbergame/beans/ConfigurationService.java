package com.numbergame.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;
    @Autowired
    public ConfigurationService(ConfigurationRepository configurationRepository){
        this.configurationRepository = configurationRepository;
    }
    public ConfigutationB addConfiguration(ConfigutationB configutationB){
        return configurationRepository.save(configutationB);
    }
    public ConfigutationB getConfiguration (String propiedadId){
        return configurationRepository.findById(propiedadId);
    }
    public List<ConfigutationB> getAllConfiguration(){
        return configurationRepository.findAll();
    }
    public ConfigutationB updateConfiguration(ConfigutationB configutationB){
        if(configurationRepository.existsById(configutationB.getId())){
            return configurationRepository.save(configutationB);
        }
        return null;
    }

    public void deleteConfiguration(Long configurationId){
        configurationRepository.deleteById(configurationId);
    }

}

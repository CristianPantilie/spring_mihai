package ro.teamnet.zthbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.teamnet.zthbo.dto.PersonDTO;
import ro.teamnet.zthbo.entity.City;
import ro.teamnet.zthbo.entity.Person;
import ro.teamnet.zthbo.mapper.PersonMapper;
import ro.teamnet.zthbo.repository.CityRepository;
import ro.teamnet.zthbo.repository.PersonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}

package com.nick.spring.services.impl;

import com.nick.spring.converter.EmployeeConverter;
import com.nick.spring.dto.EmployeeDto;
import com.nick.spring.entity.EmployeeEntity;
import com.nick.spring.exceptions.DuplicateEmailException;
import com.nick.spring.exceptions.InstanceUndefinedException;
import com.nick.spring.repositories.EmployeeRepository;
import com.nick.spring.services.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeConverter converter;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> listAll() {
        List<EmployeeEntity> employeeList = employeeRepository.findAll();
        List<EmployeeDto> returnValue = new ArrayList<>();
        for (EmployeeEntity e : employeeList) {
            returnValue.add(converter.entityToDto(e));
        }
        return returnValue;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeById(Integer employeeId) {
        EmployeeDto returnValue = null;
        Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            EmployeeEntity employeeEntity = employeeOptional.get();
            returnValue = converter.entityToDto(employeeEntity);
        } else {
            throw new InstanceUndefinedException("Employee has not being found");
        }
        return returnValue;
    }

    @Override
    @Transactional
    public EmployeeDto addEmployee(EmployeeDto employee) {
        Optional<EmployeeEntity> existingOptional = employeeRepository.findByEmail(employee.getEmail());
        if (existingOptional.isPresent()){
            throw new DuplicateEmailException("This email already exists");
        }
        EmployeeEntity storedEmployee = employeeRepository.save(converter.dtoToEntity(employee));
        return converter.entityToDto(storedEmployee);
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployee(EmployeeDto employee, Integer employeeId) {
        Optional<EmployeeEntity> existingOptional = employeeRepository.findByEmail(employee.getEmail());
        if (existingOptional.isPresent()) {
            EmployeeEntity employeeEntity = existingOptional.get();
            if (!Objects.equals(employeeEntity.getId(), employeeId)) {
                throw new DuplicateEmailException("This email already exists");
            }
        }
        EmployeeDto currentEmployee = getEmployeeById(employeeId);
        employee.setId(currentEmployee.getId());
        EmployeeEntity updatedEmployee = employeeRepository.saveAndFlush(converter.dtoToEntity(employee));
        return converter.entityToDto(updatedEmployee);
    }

    @Override
    @Transactional
    public void deleteEmployee(Integer employeeId) {
        EmployeeDto employee = getEmployeeById(employeeId);
        employeeRepository.deleteById(employeeId);
        employeeRepository.flush();
    }

    @Override
    @Transactional
    public void deleteAll() {
        employeeRepository.deleteAll();
    }

    @Override
    public double findMaximumSalary() {
        if(employeeRepository.findMaximumSalary().isPresent()){
            return employeeRepository.findMaximumSalary().get();
        }
        return 0;
    }

    /*private int generateRandomNumber() {
        Random random = new Random();

        return random.nextInt(90000) + 10000;
    }*/

   @PostConstruct
    @Transactional
    private void loadEmployees() {
        List<EmployeeDto> employeeList = List.of(
                new EmployeeDto("Liam", "Hughes", "liam.hughes@example.com", "Developer", "London", 72000),
                new EmployeeDto("Nora", "Silva", "nora.silva@example.com", "Analyst", "Lisbon", 54000),
                new EmployeeDto("Mira", "Katsaros", "mira.katsaros@example.com", "Engineer", "Athens", 68000),
                new EmployeeDto("Jonas", "Reed", "jonas.reed@example.com", "Designer", "Berlin", 59000),
                new EmployeeDto("Evan", "Clark", "evan.clark@example.com", "Developer", "Manchester", 71000),
                new EmployeeDto("Tessa", "Roman", "tessa.roman@example.com", "Engineer", "Dublin", 69000),
                new EmployeeDto("Callum", "Hayes", "callum.hayes@example.com", "Support", "Edinburgh", 47000),
                new EmployeeDto("Lara", "Becker", "lara.becker@example.com", "Analyst", "Hamburg", 56000),
                new EmployeeDto("Soren", "Vale", "soren.vale@example.com", "Engineer", "Stockholm", 75000),
                new EmployeeDto("Isla", "Cormac", "isla.cormac@example.com", "Designer", "Oslo", 60000),
                new EmployeeDto("Gavin", "Stone", "gavin.stone@example.com", "Developer", "London", 73000),
                new EmployeeDto("Maeve", "Donovan", "maeve.donovan@example.com", "HR", "Cardiff", 52000),
                new EmployeeDto("Ada", "Fernandez", "ada.fernandez@example.com", "Developer", "Madrid", 70000),
                new EmployeeDto("Victor", "Sloan", "victor.sloan@example.com", "Engineer", "Toronto", 76000),
                new EmployeeDto("Helena", "Cross", "helena.cross@example.com", "HR", "Dublin", 52000),
                new EmployeeDto("Marcus", "Wei", "marcus.wei@example.com", "Developer", "Hong Kong", 74000),
                new EmployeeDto("Selene", "Hart", "selene.hart@example.com", "Analyst", "Manchester", 55000),
                new EmployeeDto("Jonah", "Pierce", "jonah.pierce@example.com", "Engineer", "Vancouver", 80000),
                new EmployeeDto("Clive", "Ramos", "clive.ramos@example.com", "Support", "Chicago", 49000),
                new EmployeeDto("Elara", "Quinn", "elara.quinn@example.com", "HR", "Denver", 53000),
                new EmployeeDto("Orion", "Bell", "orion.bell@example.com", "Developer", "Toronto", 72000),
                new EmployeeDto("Rina", "Sato", "rina.sato@example.com", "Engineer", "Tokyo", 82000),
                new EmployeeDto("Damon", "Knox", "damon.knox@example.com", "Designer", "Seattle", 61000),
                new EmployeeDto("Alina", "Voigt", "alina.voigt@example.com", "Analyst", "Munich", 58000),
                new EmployeeDto("Dana", "Keller", "dana.keller@example.com", "Support", "Denver", 48000),
                new EmployeeDto("Felix", "Graham", "felix.graham@example.com", "Engineer", "Seattle", 83000),
                new EmployeeDto("Iris", "Muller", "iris.muller@example.com", "Designer", "Vienna", 61000),
                new EmployeeDto("Rafael", "Costa", "rafael.costa@example.com", "Manager", "Puerto Rico", 88000),
                new EmployeeDto("Bruno", "Silva", "bruno.silva@example.com", "Developer", "Lisbon", 70000),
                new EmployeeDto("Nadia", "Frost", "nadia.frost@example.com", "Engineer", "Zurich", 84000),
                new EmployeeDto("Harlan", "Beck", "harlan.beck@example.com", "Support", "Phoenix", 46000),
                new EmployeeDto("Terra", "Moss", "terra.moss@example.com", "Analyst", "Boston", 57000),
                new EmployeeDto("Cedric", "Young", "cedric.young@example.com", "Developer", "Austin", 75000),
                new EmployeeDto("Ingrid", "Berg", "ingrid.berg@example.com", "HR", "Oslo", 52000),
                new EmployeeDto("Silas", "Reed", "silas.reed@example.com", "Engineer", "Copenhagen", 81000),
                new EmployeeDto("Juno", "Arden", "juno.arden@example.com", "Designer", "Amsterdam", 62000),
                new EmployeeDto("Elin", "Ward", "elin.ward@example.com", "Analyst", "Oslo", 56000),
                new EmployeeDto("Tariq", "Nadeem", "tariq.nadeem@example.com", "Developer", "Dubai", 78000),
                new EmployeeDto("Sofia", "Laurent", "sofia.laurent@example.com", "Engineer", "Paris", 82000),
                new EmployeeDto("Owen", "Price", "owen.price@example.com", "Support", "Vancouver", 47000),
                new EmployeeDto("Lukas", "Brandt", "lukas.brandt@example.com", "Developer", "Berlin", 72000),
                new EmployeeDto("Eira", "Solberg", "eira.solberg@example.com", "Designer", "Oslo", 60000),
                new EmployeeDto("Ronan", "Dale", "ronan.dale@example.com", "Support", "Dublin", 48000),
                new EmployeeDto("Petra", "Nowak", "petra.nowak@example.com", "Analyst", "Prague", 55000),
                new EmployeeDto("Marek", "Horvat", "marek.horvat@example.com", "Engineer", "Bratislava", 79000),
                new EmployeeDto("Selma", "Khan", "selma.khan@example.com", "HR", "Dubai", 53000),
                new EmployeeDto("Jared", "Cole", "jared.cole@example.com", "Developer", "Chicago", 74000),
                new EmployeeDto("Vivian", "Shaw", "vivian.shaw@example.com", "Engineer", "London", 83000),
                new EmployeeDto("Zara", "Ivanova", "zara.ivanova@example.com", "Analyst", "Prague", 55000),
                new EmployeeDto("Hugo", "Petrov", "hugo.petrov@example.com", "Developer", "Tallinn", 71000),
                new EmployeeDto("Clara", "Nguyen", "clara.nguyen@example.com", "HR", "Melbourne", 53000),
                new EmployeeDto("Ralph", "Morgan", "ralph.morgan@example.com", "Engineer", "Wellington", 76000),
                new EmployeeDto("Ivana", "Sokol", "ivana.sokol@example.com", "Designer", "Brno", 60000),
                new EmployeeDto("Theo", "Wells", "theo.wells@example.com", "Developer", "Sydney", 72000),
                new EmployeeDto("Nina", "Kurt", "nina.kurt@example.com", "Support", "Auckland", 47000),
                new EmployeeDto("Arlo", "Bennett", "arlo.bennett@example.com", "Engineer", "Melbourne", 82000),
                new EmployeeDto("Kai", "Zimmer", "kai.zimmer@example.com", "Analyst", "Zurich", 58000),
                new EmployeeDto("Jada", "Holt", "jada.holt@example.com", "Developer", "Perth", 70000),
                new EmployeeDto("Miles", "Cooper", "miles.cooper@example.com", "Support", "Adelaide", 46000),
                new EmployeeDto("Ellie", "Grant", "ellie.grant@example.com", "Designer", "Canberra", 61000),
                new EmployeeDto("Harper", "Lee", "harper.lee@example.com", "Engineer", "San Jose", 90000),
                new EmployeeDto("Dylan", "Rose", "dylan.rose@example.com", "Developer", "San Francisco", 95000),
                new EmployeeDto("Rhea", "Singh", "rhea.singh@example.com", "Analyst", "New Delhi", 58000),
                new EmployeeDto("Elton", "Baird", "elton.baird@example.com", "Support", "Austin", 50000),
                new EmployeeDto("Chloe", "Ng", "chloe.ng@example.com", "Designer", "Singapore", 62000),
                new EmployeeDto("Viktor", "Antonov", "viktor.antonov@example.com", "Engineer", "Sofia", 82000),
                new EmployeeDto("Frida", "Lund", "frida.lund@example.com", "Developer", "Stockholm", 88000),
                new EmployeeDto("Irwin", "Katz", "irwin.katz@example.com", "HR", "San Diego", 54000),
                new EmployeeDto("Tara", "Olsen", "tara.olsen@example.com", "Analyst", "Copenhagen", 60000),
                new EmployeeDto("Gideon", "Fox", "gideon.fox@example.com", "Engineer", "Los Angeles", 92000),
                new EmployeeDto("Nova", "Reyes", "nova.reyes@example.com", "Designer", "Madrid", 61000),
                new EmployeeDto("Lance", "Porter", "lance.porter@example.com", "Support", "Portland", 49000),
                new EmployeeDto("Yara", "Lopez", "yara.lopez@example.com", "Developer", "Buenos Aires", 72000),
                new EmployeeDto("Cormac", "Reeves", "cormac.reeves@example.com", "Engineer", "Dublin", 82000),
                new EmployeeDto("Lucien", "Marchand", "lucien.marchand@example.com", "Analyst", "Paris", 60000),
                new EmployeeDto("Sabine", "Kruger", "sabine.kruger@example.com", "Support", "Berlin", 47000),
                new EmployeeDto("Otis", "Gray", "otis.gray@example.com", "Developer", "Cape Town", 69000),
                new EmployeeDto("Freja", "Holm", "freja.holm@example.com", "Designer", "Copenhagen", 62000),
                new EmployeeDto("Rocco", "Santini", "rocco.santini@example.com", "Engineer", "Rome", 83000),
                new EmployeeDto("Lian", "Zheng", "lian.zheng@example.com", "HR", "Shanghai", 53000),
                new EmployeeDto("Bryn", "Larson", "bryn.larson@example.com", "Analyst", "Reykjavik", 58000),
                new EmployeeDto("Claudia", "Voss", "claudia.voss@example.com", "Designer", "Munich", 60000),
                new EmployeeDto("Tomas", "Bergman", "tomas.bergman@example.com", "Developer", "Stockholm", 75000),
                new EmployeeDto("Helio", "Barros", "helio.barros@example.com", "Support", "Porto", 46000),
                new EmployeeDto("Aiden", "Fox", "aiden.fox@example.com", "Developer", "New York", 90000),
                new EmployeeDto("Mina", "Sato", "mina.sato@example.com", "Analyst", "Tokyo", 59000),
                new EmployeeDto("Grant", "Wolfe", "grant.wolfe@example.com", "Engineer", "Chicago", 85000),
                new EmployeeDto("Alessia", "Martini", "alessia.martini@example.com", "Designer", "Milan", 61000),
                new EmployeeDto("Simon", "Keller", "simon.keller@example.com", "Support", "Boston", 50000),
                new EmployeeDto("Darya", "Volkova", "darya.volkova@example.com", "Engineer", "Warsaw", 81000),
                new EmployeeDto("Pavel", "Sorokin", "pavel.sorokin@example.com", "Developer", "Moscow", 78000),
                new EmployeeDto("Kara", "O'Neil", "kara.oneil@example.com", "HR", "Toronto", 53000),
                new EmployeeDto("Neven", "Kovac", "neven.kovac@example.com", "Engineer", "Zagreb", 79000),
                new EmployeeDto("Flora", "Dupont", "flora.dupont@example.com", "Analyst", "Paris", 58000),
                new EmployeeDto("Sven", "Lindholm", "sven.lindholm@example.com", "Developer", "Oslo", 76000),
                new EmployeeDto("Talia", "Mizrahi", "talia.mizrahi@example.com", "Support", "Tel Aviv", 49000)
        );

        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        if(!employeeEntityList.isEmpty()){
            return;
        }
        for (EmployeeDto e : employeeList) {
            employeeEntityList.add(converter.dtoToEntity(e));
        }
        employeeRepository.saveAll(employeeEntityList);
    }


}



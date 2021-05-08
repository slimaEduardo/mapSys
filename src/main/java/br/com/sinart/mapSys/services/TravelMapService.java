package br.com.sinart.mapSys.services;

import br.com.sinart.mapSys.dto.TravelMapNewDTO;
import br.com.sinart.mapSys.entities.TravelMap;
import br.com.sinart.mapSys.repositories.TravelMapRepository;
import br.com.sinart.mapSys.services.exceptions.ObjectNotFoundException;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TravelMapService {

    @Autowired
    private TravelMapRepository repository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DestinyService destinyService;
    @Autowired
    private BusCategoryService busCategoryService;

    public List<TravelMap> findAll() {
        return repository.findAll();
    }

    @Autowired
    public DataSource dataSource;

    public TravelMap findById(Integer id) {
        Optional<TravelMap> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Mapa com id " + id + " não encontrado."));
    }

    public TravelMap insert(TravelMap obj) {
        return repository.save(obj);
    }

    public void delete(Integer id) {

        repository.deleteById(id);

    }

    public TravelMap update(Integer id, TravelMapNewDTO obj) {
        TravelMap entity = repository.getOne(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(TravelMap entity, TravelMapNewDTO obj) {
        entity.setDestiny(destinyService.findById(obj.getDestinyId()));
        entity.setCompany(companyService.findById(obj.getCompanyId()));
        entity.setBusCategory(busCategoryService.findById(obj.getBusId()));
        entity.setPassQtt(obj.getPassQtt());
        entity.setBoardingDate(obj.getBoardingDate());
        entity.setBoardingTime(obj.getBoardingTime());

    }

    public List<TravelMap> search(LocalDate initialLocaldate, LocalDate finalLocalDate) {
        return repository.findAllByBoardingDate(initialLocaldate, finalLocalDate);
    }

    public List<TravelMap> searchByDestiny(LocalDate initialLocaldate, LocalDate finalLocalDate, Integer destiny) {
        return repository.findAllByDestinyId(initialLocaldate, finalLocalDate, destiny);
    }

    public List<TravelMap> searchByCompany(LocalDate initialLocaldate, LocalDate finalLocalDate, Integer company) {
        return repository.findAllByCompanyId(initialLocaldate, finalLocalDate, company);
    }

    public List<TravelMap> searchByCategory(LocalDate initialLocaldate, LocalDate finalLocalDate, Integer category) {
        return repository.findAllByBusCategoryId(initialLocaldate, finalLocalDate, category);
    }

    public TravelMap fromDTO(TravelMapNewDTO objDto) {
        return TravelMap.builder()
                .boardingDate(objDto.getBoardingDate())
                .boardingTime(objDto.getBoardingTime())
                .passQtt(objDto.getPassQtt())
                .busCategory(busCategoryService.findById(objDto.getBusId()))
                .company(companyService.findById(objDto.getCompanyId()))
                .destiny(destinyService.findById(objDto.getDestinyId()))
                .build();
    }

    public List<TravelMap> findAllInMonth(LocalDate initialLocalDate, LocalDate finalLocalDate) {
        return repository.findAllByBoardingDate(initialLocalDate, finalLocalDate);
    }

    public File exportReport(LocalDate initialLocalDate, LocalDate finalLocalDate) throws FileNotFoundException, JRException, SQLException {
        File file = ResourceUtils.getFile("classpath:Leaf_Green.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Date start = Date.valueOf(initialLocalDate);
        Date end = Date.valueOf(finalLocalDate);
        HashMap parameters = new HashMap<>();
        parameters.put("DATA_INICIAL", start);
        parameters.put("DATA_FINAL", end);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
        JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorio.pdf");
        File file2 = new File("relatorio.pdf");
        return file2.getAbsoluteFile();
    }
}

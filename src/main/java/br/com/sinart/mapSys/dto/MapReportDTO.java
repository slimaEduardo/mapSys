package br.com.sinart.mapSys.dto;

import java.util.ArrayList;
import java.util.List;

public class MapReportDTO {

    String name;
    List<DestinationDTO> destinations = new ArrayList<>();

    public MapReportDTO(String name, List<DestinationDTO> destinations) {
        this.name = name;
        this.destinations = destinations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DestinationDTO> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DestinationDTO> destinations) {
        this.destinations = destinations;
    }
}

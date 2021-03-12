package br.com.sinart.mapSys.entities.enums;

public enum BusCategory {

    LEITO(1,"LEITO"),
    SEMILEITO(2,"SEMILEITO"),
    COMERCIAL(3,"COMERCIAL"),
    EXECUTIVO(4,"EXECUTIVO");

    private Integer cod;
    private String description;

    BusCategory(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static BusCategory toEnum(Integer cod){
        if(cod == null){
            return  null;
        }

        for (BusCategory x : BusCategory.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw  new IllegalArgumentException("Id inválido: " + cod);
    }
}

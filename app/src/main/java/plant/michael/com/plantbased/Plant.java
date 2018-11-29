package plant.michael.com.plantbased;

public class Plant {

    public String plantName;
    public String zone;
    public String enviorment;
    public String h20Cycle;
    public String location;
    public String soil;


    public Plant()
    {

    }

    public String getPlantName() {
        return plantName;
    }

    public String getZone() {
        return zone;
    }

    public String getEnviorment() {
        return enviorment;
    }

    public String getH20Cycle() {
        return h20Cycle;
    }

    public String getLocation() {
        return location;
    }

    public String getSoil() {
        return soil;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setEnviorment(String enviorment) {
        this.enviorment = enviorment;
    }


}

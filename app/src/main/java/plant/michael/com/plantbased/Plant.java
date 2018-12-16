package plant.michael.com.plantbased;

import java.util.ArrayList;

public class Plant {

    public String plantName;
    public String zone;
    public String enviorment;
    public String h20Cycle;
    public String location;
    public String soil;
    public String light;
    public String pests;
    public String funFact;
    public  ArrayList<Plant> plants = new ArrayList<>();


    public Plant()
    {

    }

    public ArrayList<Plant> getplant()
    {
        System.out.println("Size is "+plants.size());
        return plants;
    }

    public void setPlant(Plant plant)
    {
        plants.add(plant);
        System.out.println(plants.size());
        plant = plants.get(0);
        System.out.println(plant.getPlantName());
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

    public String getLight() {return light;}

    public String getPests() {return pests;}

    public String getFunFact() {return funFact;}

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setEnviorment(String enviorment) {
        this.enviorment = enviorment;
    }

    public void setH20Cycle(String h20Cycle) {
        this.h20Cycle = h20Cycle;
        }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public void setPests(String pests) {
        this.pests = pests;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public void setFunFact(String funFact) {
        this.funFact = funFact;
    }
}

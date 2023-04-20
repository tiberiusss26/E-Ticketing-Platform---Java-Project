package Model;

public class Location {
    private String name;
    private String country;
    private long capacity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public Location(String name, String country, long capacity) {
        this.name = name;
        this.country = country;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}

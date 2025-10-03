package seed;

import model.Driver;

import java.util.ArrayList;
import java.util.List;

public class DriverSeed {

    public static List<Driver> getDrivers() {
        List<Driver> drivers= new ArrayList<>();

        drivers.add(new Driver("Jeon Jung-kook", "76996084024", "59172241770"));
        drivers.add(new Driver("Park Ji-min", "00254319009", "07185732128"));
        drivers.add(new Driver("Roseanne Park", "09350974061", "45795815182"));
        drivers.add(new Driver("Kim Ji-soo", "84309334091", "95194837045"));
        drivers.add(new Driver("Jung Hoseok", "44196685032", "54879651235"));

        return drivers;
    }
}

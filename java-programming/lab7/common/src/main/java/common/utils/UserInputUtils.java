package common.utils;

import common.model.*;
import common.enumeration.*;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class UserInputUtils {
    private final Scanner sc = new Scanner(System.in);
    private final Validator v = new Validator();
    private boolean silent = false;

    public void setSilent(boolean s) { this.silent = s; }

    public Person readPerson() {
        try {
            if (!silent) System.out.print("Name: ");
            String name = v.validateNonEmptyString(sc.nextLine(), "Name");

            if (!silent) System.out.print("coord x (Long≤755): ");
            Long x = v.validateLong(sc.nextLine(), "coord x", 755L);

            if (!silent) System.out.print("coord y (int): ");
            Integer y = v.validateInteger(sc.nextLine(), "coord y");

            Coordinates coords = new Coordinates(x, y);

            if (!silent) System.out.print("height (float>0): ");
            Float h = v.validateFloat(sc.nextLine(), "height", true);

            if (!silent) System.out.print("weight (float>0 or blank): ");
            String wln = sc.nextLine().trim();
            Float w = wln.isEmpty() ? null : v.validateFloat(wln, "weight", true);

            if (!silent) System.out.println("EyeColor " + java.util.Arrays.toString(Color.values()));
            String ecin = sc.nextLine().trim();
            Color ec = ecin.isEmpty() ? null : Color.valueOf(ecin.toUpperCase());

            if (!silent) System.out.println("Nationality " + java.util.Arrays.toString(Country.values()));
            String nin = v.validateNonEmptyString(sc.nextLine(), "nationality");
            Country nat = Country.valueOf(nin.toUpperCase());

            if (!silent) System.out.print("loc x (double): ");
            Double lx = Double.parseDouble(sc.nextLine().trim());

            if (!silent) System.out.print("loc y (int): ");
            int ly = Integer.parseInt(sc.nextLine().trim());

            if (!silent) System.out.print("loc z (float): ");
            float lz = Float.parseFloat(sc.nextLine().trim());

            if (!silent) System.out.print("loc name: ");
            String ln = v.validateNonEmptyString(sc.nextLine(), "loc name");
            Location loc = new Location(lx, ly, lz, ln);

            return new Person("0", name, coords, ZonedDateTime.now(), h, w, ec, nat, loc);
        } catch (Exception e) {
            System.out.println("Input error: " + e.getMessage());
            return readPerson();
        }
    }
}

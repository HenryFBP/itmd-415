package Final.Product;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import Final.Customer.Exceptions.FormNotFilledOutException;
import Final.Product.Car.Car;
import Final.Product.Car.CarCreationServlet;
import Final.Product.Car.CarHandler;
import Final.Product.Part.Part;
import Final.Product.Part.PartCreationServlet;
import Final.Product.Part.PartHandler;

public class ProductCreationServlet
{

    /***
     * Creates a new Product.
     * 
     * @param r
     *            The HTTP Request.
     * 
     * @return A Product.
     * 
     * @throws FormNotFilledOutException
     */
    public static Product createProduct(HttpServletRequest r) throws FormNotFilledOutException
    {
        String type = r.getParameter("producttype"); // hidden parameter to differentiate between Car and Part
        String name = r.getParameter("name"); // the name, part of a Product, not a Car or Part.

        ArrayList<String> problems = new ArrayList<>();

        if(type == null || type.length() == 0)
        {
            problems.add("type");
        }

        if(name == null || name.length() == 0)
        {
            problems.add("name");
        }

        if(!problems.isEmpty())
        {
            throw new FormNotFilledOutException(problems);
        }

        System.out.printf("Making product of type '%s', named '%s'.\n", type, name);

        Part part = null;
        Car car = null;

        if(CarHandler.isCar(type)) // it's a car
        {
            System.out.println("thems makin a car!");
            car = CarCreationServlet.createCar(r);
        }
        else if(PartHandler.isPart(type)) // it's a part
        {
            System.out.println("thems makin a part!");
            part = PartCreationServlet.createPart(r);
        }
        else
        {
            throw new FormNotFilledOutException("type");
        }

        return null;
    }

}

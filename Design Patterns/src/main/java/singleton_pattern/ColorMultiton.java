package singleton_pattern;

import javax.naming.NameNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ColorMultiton {
    // a bunch of singletons which can be retrieved by name
    private static Map<String, ColorMultiton> colorSingleton;
    
    private int red;
    private int green;
    private int blue;
    private double alpha;
    
    public ColorMultiton(int red, int green, int blue, double alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }
    
    public static ColorMultiton instance(String name) throws NameNotFoundException {
        if (colorSingleton == null) {
            synchronized (ColorMultiton.class) {
                if (colorSingleton == null) {
                    colorSingleton = new HashMap<>();
                    
                    //add the singletons we support
                    colorSingleton.put("Magic Pink", new ColorMultiton(255, 0, 255, 1.0));
                    colorSingleton.put("Lime Green", new ColorMultiton(50, 205, 50, 1.0));
                    colorSingleton.put("Hot Pink", new ColorMultiton(255, 105, 180, 1.0));
                }
            }
        }
    
        if (!colorSingleton.containsKey(name)) {
            throw new NameNotFoundException("Color is missing");
        } else {
            return colorSingleton.get(name);
        }
    }
}

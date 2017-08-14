package shop.functions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TypeConverter {
    private static List<Class<?>> types = new ArrayList<>();
    public TypeConverter() {
        types.add(Integer.class);
        types.add(Double.class);
        types.add(Byte.class);
        types.add(Short.class);
        types.add(Long.class);
    }

    public Number convert(String number, Class<?> type){
        Number resNumber = null;
        if (type.isPrimitive()){
            for (Class<?> typeClass : types) {
                if (typeClass.getName().toLowerCase().contains(type.getName().toLowerCase())){
                    type = typeClass;
                    break;
                }
            }
        }
        try {
            resNumber = (Number) type.getMethod("valueOf", String.class).invoke(null, number);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            resNumber = null;
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return resNumber;
    }
}

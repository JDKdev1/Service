package magic.studio.service.customer.Model;

import java.io.Serializable;

/**
 * Created by Android on 1/14/2018.
 */

public class ServiceModel implements Serializable{
    String name;
    Integer image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

}

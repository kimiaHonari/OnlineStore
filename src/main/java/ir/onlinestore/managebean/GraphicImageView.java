package ir.onlinestore.managebean;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by kimia on 1/17/2017.
 */
@ManagedBean(name="GraphicImageView")
@ApplicationScoped
public class GraphicImageView {

   private String HomeImage;

    public String getHomeImage() {
        return HomeImage;
    }

    public void setHomeImage(String homeImage) {
        HomeImage = homeImage;
    }

    @PostConstruct
    public void init() {
        HomeImage="resources/images/Home-Image.jpg";
    }




}

package com.djcps.wms.commons.utils;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 图片处理类
 * 用于透明底缩略图
 * @author Chengw
 * @since 2017/9/12 18:56.
 */
public class ImageUtils {

    /**
     * 生成图片
     * @param inputStream 输入流
     * @param outputStream 输出流
     * @param positionX 原图X坐标
     * @param positionY 原图Y坐标
     * @param ratio 缩放比例
     * @param srcX 可视区域X坐标
     * @param srcY 可视区域Y坐标
     * @param srcWidth 可视区域宽度
     * @param srcHeight 可视区域高度
     * @param width 画布宽
     * @param height 画布高
     * @throws Exception
     */
    public static void zoomImage(InputStream inputStream, OutputStream outputStream,
                                 int positionX,int positionY,float ratio,
                                 int srcX ,int srcY, int srcWidth,int srcHeight,
                                 int width,int height) throws Exception{
        BufferedImage sourceImage = ImageIO.read(inputStream);
        BufferedImage destImage = getImage(sourceImage,positionX,positionY,ratio,srcX,srcY,srcWidth,srcHeight,width,height);

        ImageIO.write(destImage,"png",outputStream);

    }

    /**
     *
     * @param sourceImage 原图
     * @param positionX 原图X坐标
     * @param positionY 原图Y坐标
     * @param ratio 缩放比例
     * @param srcX 可视区域X坐标
     * @param srcY 可视区域Y坐标
     * @param srcWidth 可视区域宽度
     * @param srcHeight 可视区域高度
     * @param width 画布宽
     * @param height 画布高
     * @throws Exception
     */
    public static BufferedImage getImage(BufferedImage sourceImage,int positionX,int positionY,float ratio,
                                         int srcX ,int srcY, int srcWidth,int srcHeight,int width,int height) throws Exception{
        BufferedImage targetImage = zoomImage(sourceImage,ratio);
        BufferedImage destImage = getTransparentImage(width,height);
        destImage.getGraphics().drawImage(targetImage.getScaledInstance(targetImage.getWidth(),targetImage.getHeight(),Image.SCALE_SMOOTH),positionX,positionY,null);
        destImage = clipImage(destImage,srcX,srcY,srcWidth,srcHeight);
        return destImage;
    }

    /**
     * 裁切图片
     * @param sourceImage 原图
     * @param srcX 裁切起点X坐标
     * @param srcY 裁切起点Y坐标
     * @param width 裁切宽度
     * @param height 裁切高度
     * @return
     */
    public static BufferedImage clipImage(BufferedImage sourceImage,int srcX,int srcY ,int width ,int height){
        BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(sourceImage, 0, 0, width, height, srcX, srcY, srcX + width, srcY + height, null);
        g.dispose();
        return newImg;
    }

    /**
     * 缩放图片
     * @param sourceImage 原图
     * @param ratio 缩放比例
     * @return
     */
    public static BufferedImage zoomImage(BufferedImage sourceImage,float ratio){
        double imgWidth = sourceImage.getWidth() * ratio;
        double imgHeigth = sourceImage.getHeight() * ratio;
        BufferedImage targetImage = new BufferedImage((int)imgWidth,(int)imgHeigth,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = targetImage.getGraphics();
        graphics.drawImage(sourceImage,0,0,(int)imgWidth,(int)imgHeigth,null);
        graphics.dispose();
        return targetImage;
    }

    /**
     * 绘制透明背景图片
     * @param width 画布宽度
     * @param heigth 画布高度
     * @return
     */
    public static BufferedImage getTransparentImage(int width ,int heigth){
        BufferedImage destImage = new BufferedImage(width,heigth,BufferedImage.TYPE_INT_RGB);
        Graphics2D gd = destImage.createGraphics();
        gd.setColor(Color.WHITE);
        destImage = gd.getDeviceConfiguration().createCompatibleImage(width, heigth, Transparency.TRANSLUCENT);
        gd.dispose();
        return destImage;
    }



    public static void main(String[] args) throws Exception{


        ImageUtils.zoomImage(new FileInputStream("/Users/superman/Desktop/4.jpg"),
                new FileOutputStream("/Users/superman/Desktop/4_1.jpg"),100,110,0.1f,100,100,100,100,300,300);

        System.out.println("zoomImage end");
    }
}

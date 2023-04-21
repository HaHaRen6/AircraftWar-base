package edu.hitsz.application;

import java.awt.*;
import java.io.File;

public class EasyGame extends Game{
    @Override
    protected Image getBackGround() {
        return ImageManager.BACKGROUND_IMAGE1;
    }

    @Override
    protected File getScoreFile(){
        return new File("score/EasyGameScore.txt");
    }
}

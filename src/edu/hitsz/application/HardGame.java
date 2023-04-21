package edu.hitsz.application;

import java.awt.*;
import java.io.File;

public class HardGame extends Game{
    @Override
    protected Image getBackGround() {
        return ImageManager.BACKGROUND_IMAGE3;
    }

    @Override
    protected File getScoreFile(){
        return new File("score/HardGameScore.txt");
    }
}

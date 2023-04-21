package edu.hitsz.application;

import java.awt.*;
import java.io.File;

public class MediumGame extends Game{
    @Override
    protected Image getBackGround() {
        return ImageManager.BACKGROUND_IMAGE2;
    }

    @Override
    protected File getScoreFile(){
        return new File("score/MediumGameScore.txt");
    }
}

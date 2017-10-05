package com.wakaleo.gameoflife.webtests;

import com.wakaleo.gameoflife.webtests.steps.PlayerSteps;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(ThucydidesRunner.class)

@WithTag("Configuring a simulation")
public class WhenTheUserSpawnsTheNextGeneration {

    @Managed(uniqueSession = true)
    WebDriver driver;

    @ManagedPages(defaultUrl = "http://localhost:9090")
    public Pages pages;

    @Steps
    PlayerSteps player;

    @Test
    public void anEmptyGridShouldProduceAnEmptyGrid() {
        String[][] expectedGrid = new String[][]{{".", ".", "."},
                {".", ".", "."},
                {".", ".", "."}};

        player.opensHomePage();
        player.choosesToStartANewGame();
        player.startsSimulation();
        player.continuesSimulation();
        player.shouldSeeGrid(expectedGrid);

    }

    @Test
    public void aGridWithOneCellShouldProduceAnEmptyGrid() {
        String[][] expectedGrid = new String[][]{{".", ".", "."},
                {".", ".", "."},
                {".", ".", "."}};

        player.opensHomePage();
        player.choosesToStartANewGame();
        player.clicksOnCellAt(1, 1);
        player.startsSimulation();
        player.continuesSimulation();
        player.shouldSeeGrid(expectedGrid);
    }

    @Test
    public void aGridWithTwoCellsShouldProduceAnEmptyGrid() {
        String[][] expectedGrid = new String[][]{{".", ".", "."},
                {".", ".", "."},
                {".", ".", "."}};

        player.opensHomePage();
        player.choosesToStartANewGame();
        player.clicksOnCellAt(1, 1);
        player.clicksOnCellAt(0, 1);
        player.startsSimulation();
        player.continuesSimulation();
        player.shouldSeeGrid(expectedGrid);
    }


    @Test
    public void aStableCellSetShouldProduceTheSameSetOfCells() {
        String[][] expectedGrid = new String[][]{{"*", "*", "."},
                {"*", "*", "."},
                {".", ".", "."}};

        player.opensHomePage();
        player.choosesToStartANewGame();
        player.clicksOnCellAt(0, 0);
        player.clicksOnCellAt(0, 1);
        player.clicksOnCellAt(1, 0);
        player.clicksOnCellAt(1, 1);
        player.startsSimulation();
        player.continuesSimulation();
        player.shouldSeeGrid(expectedGrid);
    }

    @Test
    public void aRotatingCellSetShouldProduceTheExpectedNewSetOfCells() {
        String[][] expectedGrid = new String[][]{{".", ".", "."},
                {".", "*", "."},
                {".", "*", "."}};

        player.opensHomePage();
        player.choosesToStartANewGame();
        player.clicksOnCellAt(0, 0);
        player.clicksOnCellAt(0, 1);
        player.clicksOnCellAt(1, 0);
        player.clicksOnCellAt(1, 1);
        player.clicksOnCellAt(1, 2);
        player.startsSimulation();
        player.continuesSimulation();
        player.continuesSimulation();
        player.continuesSimulation();
        player.shouldSeeGrid(expectedGrid);
    }

    @Test
    public void aRotatingCellSetShouldProduceTheOriginalSetOfCellsAfterTwoGenerations() {
        String[][] expectedGrid = new String[][]{{".", ".", "."},
                {"*", "*", "*"},
                {".", ".", "."}};

        player.opensHomePage();
        player.choosesToStartANewGame();
        player.clicksOnCellAt(1, 0);
        player.clicksOnCellAt(1, 1);
        player.clicksOnCellAt(1, 2);
        player.startsSimulation();
        player.continuesSimulation();
        player.continuesSimulation();
        player.shouldSeeGrid(expectedGrid);
    }

    @Test
    public void aRotatingCellSetShouldProduceTheOriginalSetOfCellsAfterThreeGenerations() {
        String[][] expectedGrid = new String[][]{{".", "*", "."},
                {".", "*", "."},
                {".", "*", "."}};

        player.opensHomePage();
        player.choosesToStartANewGame();
        player.clicksOnCellAt(1, 0);
        player.clicksOnCellAt(1, 1);
        player.clicksOnCellAt(1, 2);
        player.startsSimulation();
        player.continuesSimulation();
        player.continuesSimulation();
        player.continuesSimulation();
        player.shouldSeeGrid(expectedGrid);
    }

}

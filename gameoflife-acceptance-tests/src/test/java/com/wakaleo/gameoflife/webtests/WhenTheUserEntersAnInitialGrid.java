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
@WithTag("Starting a new simulation")
public class WhenTheUserEntersAnInitialGrid {

    @Managed(uniqueSession = true)
    WebDriver driver;

    @ManagedPages(defaultUrl = "http://localhost:9090")
    public Pages pages;

    @Steps
    PlayerSteps player;


    private static final String[][] EMPTY_GRID
            = new String[][]{{".", ".", "."},
            {".", ".", "."},
            {".", ".", "."}};


    @Test
    public void userShouldBeAbleChooseToCreateANewGameOnTheHomePage() {
        player.opensHomePage();
        player.choosesToStartANewGame();
        player.shouldSeeAPageContainingText("Please seed your universe");

    }

    @Test
    public void userShouldBeAbleToSeedAnEmptyGridOnTheNewGamePage() {
        player.opensHomePage();
        player.choosesToStartANewGame();
        player.startsSimulation();
        player.shouldSeeGrid(EMPTY_GRID);
    }

    @Test
    public void theGridDisplayPageShouldContainANextGenerationButton() {
        player.opensHomePage();
        player.choosesToStartANewGame();
        player.startsSimulation();
        player.shouldSeeAPageContainingText("Next Generation");
    }

    @Test
    public void userShouldBeAbleToEnterOneLiveCellInTheGrid() {
        player.opensHomePage();
        player.choosesToStartANewGame();
        player.clicksOnCellAt(1, 1);
        player.startsSimulation();

        String[][] expectedGrid = new String[][]{{".", ".", "."},
                {".", "*", "."},
                {".", ".", "."}};

        player.shouldSeeGrid(expectedGrid);
    }

    @Test
    public void userShouldBeAbleToEnterLiveCellsInTheGrid() {
        player.opensHomePage();
        player.choosesToStartANewGame();
        player.clicksOnCellAt(0, 0);
        player.clicksOnCellAt(0, 1);
        player.clicksOnCellAt(1, 1);
        player.startsSimulation();

        String[][] expectedGrid = new String[][]{{"*", "*", "."},
                {".", "*", "."},
                {".", ".", "."}};

        player.shouldSeeGrid(expectedGrid);
    }


    @Test
    public void theGridPageShouldHaveALinkBackToTheHomePage() {
        player.opensHomePage();
        player.choosesToStartANewGame();
        player.clicksOnHome();
		player.shouldSeeAPageContainingText("Welcome to Conway's Game Of Life");
	}
}

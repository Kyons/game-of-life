package com.wakaleo.gameoflife.webtests.steps;

import com.wakaleo.gameoflife.webtests.pages.EnterGridPage;
import com.wakaleo.gameoflife.webtests.pages.GameOfLifePage;
import com.wakaleo.gameoflife.webtests.pages.HomePage;
import com.wakaleo.gameoflife.webtests.pages.ShowGridPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;

import static org.fest.assertions.Assertions.assertThat;

public class PlayerSteps {

    private Pages pages;

    @Step
    public void opensHomePage() {
        onHomePage().open();
    }

    @Step
    public void shouldSeeTitleOf(String expectedTitle) {
        assertThat(currentPage().getTitle()).contains(expectedTitle);
    }

    @Step
    public void choosesToStartANewGame() {
        onHomePage().clickOnNewGameLink();
    }

    @Step
    public void shouldSeeAPageContainingText(String expectedText) {
        currentPage().shouldContainText(expectedText);
    }

    @Step
    public void shouldSeeGrid(String[][] expectedGrid) {
        assertThat(onShowGridPage().getDisplayedGrid()).isEqualTo(expectedGrid);
    }

    @Step
    public void startsSimulation() {
        onEnterGridPage().clickOnGoButton();
    }

    @Step
    public void clicksOnCellAt(int row, int column) {
        onEnterGridPage().clickOnCellAt(row, column);
    }

    @Step
    public void clicksOnHome() {
        currentPage().clickOnHome();
    }

    private HomePage onHomePage() {
        return pages.currentPageAt(HomePage.class);
    }

    private EnterGridPage onEnterGridPage() {
        return pages.currentPageAt(EnterGridPage.class);
    }

    private ShowGridPage onShowGridPage() {
        return pages.currentPageAt(ShowGridPage.class);
    }

    private GameOfLifePage currentPage() {
        return pages.get(GameOfLifePage.class);
    }

    public void continuesSimulation() {
        onShowGridPage().clickOnNextGenerationButton();
    }
}

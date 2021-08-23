package org.example.framework.managers;


import org.example.framework.pages.ContributionsPage;
import org.example.framework.pages.HomePage;

/**
 * Класс для управления страничками
 */
public class PageManager {

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страница
     */
    private HomePage homePage;

    /**
     *  Страница Вклады
     */
    private ContributionsPage contributionsPage;

    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link HomePage}
     * @return HomePage
     */
    public HomePage getHomePage() {
        if(homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    /**
     * Ленивая инициализация {@link ContributionsPage}
     *
     * @return ContributionsPage
     */
    public ContributionsPage getContributionsPage() {
        if (contributionsPage == null) {
            contributionsPage = new ContributionsPage();
        }
        return contributionsPage;
    }
}
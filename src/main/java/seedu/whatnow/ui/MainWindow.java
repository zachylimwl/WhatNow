package seedu.whatnow.ui;

//@@author A0139772U
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.whatnow.commons.core.Config;
import seedu.whatnow.commons.core.GuiSettings;
import seedu.whatnow.commons.events.ui.ExitAppRequestEvent;
import seedu.whatnow.logic.Logic;
import seedu.whatnow.model.UserPrefs;

/**
 * The Main Window. Provides the basic application layout containing a menu bar
 * and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart {

    private static final String ICON = "/images/WhatNowWhiteOnBlue.png";
    private static final String FXML = "MainWindow.fxml";
    public static final int MIN_HEIGHT = 600;
    public static final int MIN_WIDTH = 1200;

    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ScheduleListPanel scheduleListPanel;
    private TaskListPanel taskListPanel;
    private ResultDisplay resultDisplay;
    private StatusBarFooter statusBarFooter;
    private CommandBox commandBox;
    private Config config;
    private UserPrefs userPrefs;
    private StatusPanel statusPanel;
    private PinnedItemPanel pinnedItemPanel;

    // Handles to elements of this Ui container
    private VBox rootLayout;
    private Scene scene;

    @FXML
    private AnchorPane scheduleListPlaceholder;

    @FXML
    private AnchorPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private AnchorPane taskListPanelPlaceholder;

    @FXML
    private AnchorPane resultDisplayPlaceholder;

    @FXML
    private AnchorPane statusbarPlaceholder;
    
    @FXML
    private AnchorPane statusPanelPlaceholder;
    
    @FXML
    private AnchorPane pinnedItemPlaceholder;
    
    @FXML
    private GridPane gridPane;

    public MainWindow() {
        super();
    }

    @Override
    public void setNode(Node node) {
        rootLayout = (VBox) node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }

    public static MainWindow load(Stage primaryStage, Config config, UserPrefs prefs, Logic logic) {

        MainWindow mainWindow = UiPartLoader.loadUiPart(primaryStage, new MainWindow());
        mainWindow.configure(config.getAppTitle(), config.getWhatNowName(), config, prefs, logic);
        return mainWindow;
    }

    private void configure(String appTitle, String whatNowName, Config config, UserPrefs prefs, Logic logic) {

        // Set dependencies
        this.logic = logic;
        this.config = config;
        this.userPrefs = prefs;
        // Configure the UI
        setTitle(appTitle);
        setIcon(ICON);
        setWindowMinSize();
        setWindowDefaultSize(prefs);
        
        
        scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        setAccelerators();
        
    }

    private void setAccelerators() {
        helpMenuItem.setAccelerator(KeyCombination.valueOf("F1"));
    }

    void fillInnerParts() {
        pinnedItemPanel = PinnedItemPanel.load(primaryStage, 
                getPinnedListPlaceholder(), 
                logic.getPinnedItems(config.getPinnedItemType(), config.getPinnedItemKeyword()));
        statusPanel = StatusPanel.load(primaryStage, getStatusPanelPlaceholder(), getGridPane());
        statusPanel.postMessage("Number of ongoing tasks in schedule: " + String.valueOf(logic.getFilteredScheduleList().size()
                + "\n"
                + "Number of overdue tasks in schedule: " + String.valueOf(logic.getOverdueScheduleList().size())));
        scheduleListPanel = ScheduleListPanel.load(primaryStage, getScheduleListPlaceholder(),
                logic.getFilteredScheduleList());
        taskListPanel = TaskListPanel.load(primaryStage, getTaskListPlaceholder(), logic.getFilteredTaskList());
        resultDisplay = ResultDisplay.load(primaryStage, getResultDisplayPlaceholder());
        statusBarFooter = StatusBarFooter.load(primaryStage, getStatusbarPlaceholder(), config.getWhatNowFilePath());
        commandBox = CommandBox.load(primaryStage, getCommandBoxPlaceholder(), resultDisplay, logic);
    }

    private AnchorPane getCommandBoxPlaceholder() {
        return commandBoxPlaceholder;
    }

    private AnchorPane getStatusbarPlaceholder() {
        return statusbarPlaceholder;
    }

    private AnchorPane getResultDisplayPlaceholder() {
        return resultDisplayPlaceholder;
    }

    public AnchorPane getTaskListPlaceholder() {
        return taskListPanelPlaceholder;
    }

    private AnchorPane getScheduleListPlaceholder() {
        return scheduleListPlaceholder;
    }
    
    private AnchorPane getStatusPanelPlaceholder() {
        return statusPanelPlaceholder;
    }
    
    private AnchorPane getPinnedListPlaceholder() {
        return pinnedItemPlaceholder;
    }
    
    private GridPane getGridPane() {
        return gridPane;
    }

    public void hide() {
        primaryStage.hide();
    }

    private void setTitle(String appTitle) {
        primaryStage.setTitle(appTitle);
    }

    /**
     * Sets the default size based on user preferences.
     */
    protected void setWindowDefaultSize(UserPrefs prefs) {
        primaryStage.setHeight(prefs.getGuiSettings().getWindowHeight());
        primaryStage.setWidth(prefs.getGuiSettings().getWindowWidth());
        if (prefs.getGuiSettings().getWindowCoordinates() != null) {
            primaryStage.setX(prefs.getGuiSettings().getWindowCoordinates().getX());
            primaryStage.setY(prefs.getGuiSettings().getWindowCoordinates().getY());
        }
    }

    private void setWindowMinSize() {
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
    }

    /**
     * Returns the current size and the position of the main Window.
     */
    public GuiSettings getCurrentGuiSetting() {
        return new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(), (int) primaryStage.getX(),
                (int) primaryStage.getY());
    }

    @FXML
    public void handleHelp() {
        HelpWindow helpWindow = HelpWindow.load(primaryStage);
        helpWindow.show();
    }

    public void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        raise(new ExitAppRequestEvent());
    }

    public TaskListPanel getTaskListPanel() {
        return this.taskListPanel;
    }

    public ScheduleListPanel getScheduleListPanel() {
        return this.scheduleListPanel;
    }
    
    public StatusPanel getStatusPanel() {
        return this.statusPanel;
    }
    
    public PinnedItemPanel getPinnedItemPanel() {
        return this.pinnedItemPanel;
    }
}


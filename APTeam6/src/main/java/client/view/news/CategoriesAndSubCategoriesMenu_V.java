package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;
import java.util.Arrays;

public class CategoriesAndSubCategoriesMenu_V extends GeneralController_V {
    @Override
    public void start() {
        senderReceiver.changeMenu(0);
        senderReceiver.sendMessage("12-0");
        String receipt = senderReceiver.getMessage();
        role = Integer.parseInt(String.valueOf(receipt.charAt(0)));
        ArrayList<String> allCategories = new ArrayList<>(Arrays.asList(receipt.substring(4).split("---")));
        reset(allCategories);
    }

    public void reset(ArrayList<String> allCategories) {
        state = 0;
        listView.getItems().clear();
        listView.getItems().addAll(allCategories);
    }

    public ListView<String> listView;
    public ListView<String> attributesListView;

    private int state;
    private int role;
    //TODO: These

    public void initialize() {
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void backBtn(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(10).start();
    }

    public void addBtn(ActionEvent actionEvent) {
        if (role == 2 && state == 2) {
            senderReceiver.allControllers.get(13).start();
        }
        else if (role == 3) {
            if (state == 0) {
                CategoryAndSubCategoryGenerator_V generator = (CategoryAndSubCategoryGenerator_V) senderReceiver.allControllers.get(1);
                generator.setSubCategory(false);
                generator.start();
            }
            else if (state == 1) {
                CategoryAndSubCategoryGenerator_V generator = (CategoryAndSubCategoryGenerator_V) senderReceiver.allControllers.get(1);
                generator.setSubCategory(true);
                generator.start();
            }
        }
    }

    public void removeBtn(ActionEvent actionEvent) {
        int index = listView.getSelectionModel().getSelectedIndices().get(0);
        if (listView.getSelectionModel().getSelectedIndices().size() == 1 && role == 3) {
            if (state == 0) {
                senderReceiver.sendMessage("12-4" + index);
                senderReceiver.getMessage();
            }
            //TODO: subs
        }
    }

    public void editBtn(ActionEvent actionEvent) {
        if (listView.getSelectionModel().getSelectedIndices().size() == 1 && role == 3) {
            //TODO: (message) Later
        }
    }

    public void openBtn(ActionEvent actionEvent) {
        int index = listView.getSelectionModel().getSelectedIndices().get(0);
        if (listView.getSelectionModel().getSelectedIndices().size() == 1) {
            if (state == 0) {
                senderReceiver.sendMessage("12-1" + index);
                String catMessage = senderReceiver.getMessage();
                ArrayList<String> subNames = new ArrayList<>(Arrays.asList(catMessage.split("@")[0].split("---")));
                ArrayList<String> catAttributes = new ArrayList<>(Arrays.asList(catMessage.split("@")[1].split("---")));
                listView.getItems().clear();
                listView.getItems().addAll(subNames);
                attributesListView.getItems().clear();
                attributesListView.getItems().addAll(catAttributes);
                state = 1;
            }
            else if (state == 1) {
                senderReceiver.sendMessage("12-5" + index);
                String subMessage = senderReceiver.getMessage();
                ArrayList<String> proNames = new ArrayList<>(Arrays.asList(subMessage.split("@")[0].split("---")));
                ArrayList<String> subAttributes = new ArrayList<>(Arrays.asList(subMessage.split("@")[1].split("---")));
                listView.getItems().clear();
                listView.getItems().addAll(proNames);
                attributesListView.getItems().clear();
                attributesListView.getItems().addAll(subAttributes);
                state = 2;
            }
            else if (state == 2) {
                senderReceiver.sendMessage("12-9" + index);
                senderReceiver.allControllers.get(14).start();
            }
        }
    }
}

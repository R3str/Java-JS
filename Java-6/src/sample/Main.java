package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        TableView<Country> table = new TableView<Country>();
//========== Создание колонок ==========
        TableColumn<Country, String> countryNameCol //
                = new TableColumn<Country, String>("Имя");
        countryNameCol.setMinWidth(180);

        TableColumn<Country, String> slugCol//
                = new TableColumn<Country, String>("Слаг");
        slugCol.setMinWidth(180);

        TableColumn<Country, String> iso2Col//
                = new TableColumn<Country, String>("ISO2");
        iso2Col.setMinWidth(50);

//========== Привязка колонок таблицы к полям Country ==========
        countryNameCol.setCellValueFactory(new PropertyValueFactory<>("nameCountry"));
        slugCol.setCellValueFactory(new PropertyValueFactory<>("slug"));
        iso2Col.setCellValueFactory(new PropertyValueFactory<>("ISO2"));

//========== Добавление данных в таблицу ==========
        table.setItems( GetData() );
        table.getColumns().addAll(countryNameCol, slugCol, iso2Col);

        StackPane root = new StackPane();
        root.setPadding(new Insets(5));
        root.getChildren().add(table);

        primaryStage.setTitle("Список стран");
        primaryStage.setScene(new Scene(root, 435, 708));
        primaryStage.show();
    }

//========== Получение данных с API ==========
    private ObservableList<Country> GetData() throws Exception {
        ObservableList<Country> list = FXCollections.observableArrayList();

//========== Переход по ссылке и считывание ==========
        com.company.JSONGetter.url = "https://api.covid19api.com/countries";
        String jsonData = com.company.JSONGetter.ConnectAndGet();

//========== Перевод строки в JSON массив ==========
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(jsonData);

        Countries countries = new Countries();

//========== Заполнение основного массива ==========
        for (Object jsonObject : jsonArray)
        {
            JSONObject current = (JSONObject) jsonObject;

            String nameCountry = (String) current.get("Country");
            String slug = (String) current.get("Slug");
            String iSO2 = (String) current.get("ISO2");

            list.add( new Country(nameCountry, slug, iSO2) );
        }

        return list;
    }
}

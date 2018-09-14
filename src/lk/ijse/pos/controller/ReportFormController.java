/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.controller;

import static com.lowagie.text.xml.simpleparser.EntitiesToSymbol.map;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pos.db.DBConnection;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author slash
 */
public class ReportFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button btnCustomerReport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void navigateToHome(MouseEvent event) throws IOException {
                       if (event.getSource() instanceof ImageView) {
            ImageView img = (ImageView) event.getSource();
            Parent root = null;
            switch (img.getId()) {
                case "imgHome":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pos/view/MainForm.fxml"));
                    break;
                

            }
            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();
                 primaryStage.show();
                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }

        }
    }

    @FXML
    private void onCustomerReportbtnClick(ActionEvent event) {
         try {
            InputStream stm = getClass().getResourceAsStream("/lk/ijse/pos/Report/PosCustomerReport.jasper");
            JasperPrint jasp = JasperFillManager.fillReport(stm, map,DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasp,false);
        } catch (Exception ex) {
            Logger.getLogger(ReportFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onItemReportGenerateBtn(ActionEvent event) {
        try {
            InputStream stm = getClass().getResourceAsStream("/lk/ijse/pos/Report/PosItemListReport.jasper");
            JasperPrint jasp = JasperFillManager.fillReport(stm, map,DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasp,false);
        } catch (Exception ex) {
            Logger.getLogger(ReportFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

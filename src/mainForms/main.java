package mainForms;

import control.FileControler;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import control.MD5Checksum;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.event.DocumentListener;

/* Danilo Salcedo
   Ultima modificacion  05/07/2024
Comentario:
    Generar el archivo .zip para el sistema SPI BCE
 */
public class main extends javax.swing.JFrame {

    public MD5Checksum md = new MD5Checksum();
    public FileControler fc = new FileControler();
    public String rutaSPI = "";
    public String nombreSPI = "";
    public String md5 = "";
    public Path tempDir = null;
    String archivoSPI = "";

    public main() {
        
        initComponents();
        this.setResizable(false);
        Image icon = new ImageIcon(getClass().getResource("/resources/md5ICON.png")).getImage();
        this.setIconImage(icon);
        this.rutaArchivoSPI.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }

                Transferable transferable = support.getTransferable();
                try {
                    @SuppressWarnings("unchecked")
                    List<File> files = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    if (files != null && files.size() > 0) {
                        for (File file : files) {
                            rutaArchivoSPI.setText(file.getAbsolutePath());
                            System.out.println("Archivo soltado: " + file.getAbsolutePath());
                        }
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        this.jPanelMD5.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }

                Transferable transferable = support.getTransferable();
                try {
                    @SuppressWarnings("unchecked")
                    List<File> files = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    if (files != null && files.size() > 0) {
                        for (File file : files) {
                            rutaArchivoSPI.setText(file.getAbsolutePath());
                            System.out.println("Archivo soltado: " + file.getAbsolutePath());
                        }
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        SwingUtilities.invokeLater(() -> {
            centerWindow(this);
        });

        this.btnDescargarZIP.setEnabled(false);

        this.rutaArchivoSPI.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleChange();
            }

            private void handleChange() {
                String ruta = rutaArchivoSPI.getText();
                md5ArchivoSPI.setText("");
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelMD5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rutaArchivoSPI = new javax.swing.JTextField();
        buscarSPI = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        md5ArchivoSPI = new javax.swing.JTextField();
        btnGenerarMD5 = new javax.swing.JButton();
        btnDescargarZIP = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Genera Archivo MD5");
        setIconImages(null);

        jLabel1.setText("Archivo SPI:");

        buscarSPI.setText("Buscar");
        buscarSPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarSPIActionPerformed(evt);
            }
        });

        jLabel2.setText("MD5 :");

        btnGenerarMD5.setText("Generar");
        btnGenerarMD5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarMD5ActionPerformed(evt);
            }
        });

        btnDescargarZIP.setText("Descargar Zip");
        btnDescargarZIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarZIPActionPerformed(evt);
            }
        });

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMD5Layout = new javax.swing.GroupLayout(jPanelMD5);
        jPanelMD5.setLayout(jPanelMD5Layout);
        jPanelMD5Layout.setHorizontalGroup(
            jPanelMD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMD5Layout.createSequentialGroup()
                .addGroup(jPanelMD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMD5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelMD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(md5ArchivoSPI)
                            .addComponent(rutaArchivoSPI, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelMD5Layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(btnDescargarZIP)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanelMD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGenerarMD5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buscarSPI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanelMD5Layout.setVerticalGroup(
            jPanelMD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMD5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(rutaArchivoSPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarSPI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(md5ArchivoSPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarMD5))
                .addGap(42, 42, 42)
                .addGroup(jPanelMD5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDescargarZIP)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("MD5", jPanelMD5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarSPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarSPIActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            this.rutaArchivoSPI.setText(selectedFile.getAbsolutePath());
            System.out.println("Ruta :  " + selectedFile.getAbsolutePath());
        } else {
            JOptionPane.showMessageDialog(null, "Selección de archivo cancelada.");
            System.out.println("Selección de archivo cancelada.");
        }
    }//GEN-LAST:event_buscarSPIActionPerformed

    private void btnGenerarMD5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarMD5ActionPerformed

        if (this.rutaArchivoSPI.getText().length() > 0 && this.rutaArchivoSPI != null) {

            try {
                this.tempDir = Files.createTempDirectory("tempSPI");
                System.out.println("Carpeta temporal: " + tempDir);
                this.nombreSPI = fc.getFileNameWithoutExtension(this.rutaArchivoSPI.getText());
                System.out.println("nombre : " + nombreSPI);
                md5 = md.getMD5Checksum(this.rutaArchivoSPI.getText());
                System.out.println("MD5: " + md5);
                this.md5ArchivoSPI.setText(md5);
                this.archivoSPI = fc.createFile(tempDir.toString(), this.nombreSPI, this.md5ArchivoSPI.getText());
                if (this.md5.trim().length() > 0) {
                    this.btnDescargarZIP.setEnabled(true);
                }
            } catch (Exception ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No seleciono ningun archivo");
            this.md5ArchivoSPI.setText("");
        }
    }//GEN-LAST:event_btnGenerarMD5ActionPerformed

    private void btnDescargarZIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarZIPActionPerformed

        if (this.md5ArchivoSPI.getText().trim().length() > 0 && this.md5ArchivoSPI != null) {

            String[] filesToZip = {this.rutaArchivoSPI.getText(), this.archivoSPI};
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            int option = fileChooser.showDialog(null, "Seleccionar carpeta");
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedDirectory = fileChooser.getSelectedFile();
                String zipFile = selectedDirectory.getAbsolutePath() + "\\" + nombreSPI + ".zip";
                System.out.println(zipFile);
                System.out.println("Carpeta seleccionada: " + selectedDirectory.getAbsolutePath());
                try {
                    try (FileOutputStream fos = new FileOutputStream(zipFile); ZipOutputStream zos = new ZipOutputStream(fos)) {
                        for (String file : filesToZip) {
                            System.out.println("archivos a comprimir " + file);
                            fc.addToZipFile(file, zos);
                        }
                    }
                    System.out.println("Compresión completada. Archivo ZIP creado: " + zipFile);
                    JOptionPane.showMessageDialog(null, "Compresión completada. Archivo ZIP creado: " + zipFile);
                    fc.deleteTEMPFiles(tempDir);
                    this.btnDescargarZIP.setEnabled(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("Selección de carpeta cancelada por el usuario.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Uno de los campos no completado");
        }
    }//GEN-LAST:event_btnDescargarZIPActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.rutaArchivoSPI.setText("");
        this.md5ArchivoSPI.setText("");
        this.btnDescargarZIP.setEnabled(false);
        this.rutaSPI = "";
        this.nombreSPI = "";
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void centerWindow(Window window) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = window.getSize();
        int x = (screenSize.width - windowSize.width) / 2;
        int y = (screenSize.height - windowSize.height) / 2;
        window.setLocation(x, y);
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new main().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescargarZIP;
    private javax.swing.JButton btnGenerarMD5;
    private javax.swing.JButton buscarSPI;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelMD5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField md5ArchivoSPI;
    private javax.swing.JTextField rutaArchivoSPI;
    // End of variables declaration//GEN-END:variables
}

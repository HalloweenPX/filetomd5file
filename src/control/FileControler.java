package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JOptionPane;

public class FileControler {

    public String createFile(String rutaArchivo, String nombreArchivo, String md5) {

        String filePath = rutaArchivo + "\\" + nombreArchivo + ".md5";

        try {
            File file = new File(filePath);

            file.getParentFile().mkdirs();

            // Verificar si el archivo ya existe
            if (file.createNewFile()) {
                System.out.println("Archivo creado: " + file.getAbsolutePath());
            } else {
                System.out.println("El archivo ya existe.");
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(md5.trim());
            bw.close();

            System.out.println("Se ha escrito en el archivo correctamente.");

        } catch (IOException e) {
            filePath = null;
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return filePath;
    }

    public String getFileNameWithoutExtension(String ruraDelArchivo) {
        
        File file = new File(ruraDelArchivo);
        
        if (file == null) {
            return null;
        }

        String fileName = file.getName();

        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return fileName;
        } else {
            return fileName.substring(0, dotIndex);
        }
    }

    public void addToZipFile(String filePath, ZipOutputStream zos)  {
        try {
            File file = new File(filePath);
            String entryName = file.getName();
            ZipEntry zipEntry = new ZipEntry(entryName);
            zos.putNextEntry(zipEntry);
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, bytesRead);
                }
            }
            zos.closeEntry();
        } catch (IOException ex) {
            Logger.getLogger(FileControler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static void zipDirectory(String sourceDirPath, String rootDirPath, ZipOutputStream zos) {
        File sourceDir = new File(sourceDirPath);
        File[] files = sourceDir.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                zipDirectory(file.getAbsolutePath(), rootDirPath, zos);
            } else {
                FileInputStream fis = null;
                try {
                    String entryName = file.getAbsolutePath().substring(rootDirPath.length() + 1);
                    ZipEntry zipEntry = new ZipEntry(entryName);
                    zos.putNextEntry(zipEntry);
                    fis = new FileInputStream(file);
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        zos.write(buffer, 0, bytesRead);
                    }   fis.close();
                    zos.closeEntry();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FileControler.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (IOException ex) {
                    Logger.getLogger(FileControler.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } finally {
                    try {
                        fis.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FileControler.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        }
    }

    public void saveToFile(File file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.close();
            System.out.println("Datos guardados en: " + file.getAbsolutePath());
        } catch (IOException e) {
            Logger.getLogger(FileControler.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void deleteTEMPFiles(Path tempDir) {
        try {
            if (!Files.isDirectory(tempDir)) {
                System.out.println("El directorio no es una carpeta o no existe.");
                return;
            }
            Files.walkFileTree(tempDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file); // Eliminar archivo
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir); // Eliminar directorio
                    return FileVisitResult.CONTINUE;
                }
            });
            Files.deleteIfExists(tempDir);
            System.out.println("Carpeta temporal eliminada correctamente.");
        } catch (IOException e) {
            Logger.getLogger(FileControler.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void detailFiles(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            String fileName = file.getName();
            System.out.println("Nombre del archivo: " + fileName);
            String absolutePath = file.getAbsolutePath();
            System.out.println("Ruta absoluta: " + absolutePath);
            long fileSize = file.length();
            System.out.println("Tamaño del archivo: " + fileSize + " bytes");
            long lastModifiedTimestamp = file.lastModified();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String lastModifiedDate = sdf.format(lastModifiedTimestamp);
            System.out.println("Última fecha de modificación: " + lastModifiedDate);
        } else {
            System.out.println("El archivo no existe.");
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufjf.mips.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import org.apache.commons.io.FilenameUtils;


/**
 *
 * @author Nova
 */
public class FileValidator {
    
    public void validarArquivo(String nomeArquivo)throws FileNotFoundException, IOException{
        validarArquivoNaMaquina(nomeArquivo);
        //validarTamanhoDoArquivo(nomeArquivo);  
        validarExtensao(nomeArquivo);        
        //validarArquivo(nomeArquivo);
        //verificarVirus(nomeArquivo);
    }

    

    private void validarExtensao(String nomeArquivo) throws IOException {
        String extensaoDoArquivo = FilenameUtils.getExtension(nomeArquivo);
        if(!extensaoDoArquivo.equals("txt")) {
            throw new IOException("Extensão inválida (aceito somente .txt).");
        }

    }

    private void validarTamanhoDoArquivo(String nomeArquivo) throws IOException {
        long tamanhoDoArquivo = new File(nomeArquivo).length();
        if (tamanhoDoArquivo > 1048576)
            throw new IOException("Tamanho do arquivo maior que o permitido (1MB).");
    }

//    private void verificarVirus(String nomeArquivo) throws IOException {
//        ClamavClient client = new ClamavClient("localhost");
//        ScanResult result = client.scan(new FileInputStream(nomeArquivo));
//        if (result instanceof ScanResult.VirusFound) {
//            throw new IOException("Arquivo não permitido por segurança");
//        }
//    }
    
    private void validarMimeArquivo(String nomeArquivo) throws IOException {
        File file = new File(nomeArquivo);
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        String mime = URLConnection.guessContentTypeFromStream(is);
        if(mime == null || !mime.equals("image/jpeg")){
            throw new IOException("Arquivo não é válido.");
        }
    }

    private void validarArquivoNaMaquina(String nomeArquivo) throws IOException{
        File file = new File(nomeArquivo);
        if(!file.isFile())
            throw new IOException("Arquivo não existe.");
    }


}

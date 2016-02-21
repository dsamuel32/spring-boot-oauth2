/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diego.oauth.server.utils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Diego NOTE
 */
public class DataUtil {
    
    
    public static Date gerarTempoExpiracaoToken(Date dataAtual, Long peridoTokenValido) {
        Calendar calendar = Calendar.getInstance();

        Long tempoExpirar = dataAtual.getTime() + peridoTokenValido;

        calendar.setTimeInMillis(tempoExpirar);
        
        return calendar.getTime();
    }
}

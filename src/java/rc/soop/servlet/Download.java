/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.soop.servlet;

import rc.soop.action.ActionB;
import static rc.soop.action.ActionB.trackingAction;
import rc.soop.action.Constant;
import static rc.soop.action.Constant.bando;
import rc.soop.action.Pdf_new;
import static rc.soop.action.Pdf_new.domanda;
import rc.soop.db.Db_Bando;
import rc.soop.entity.AllegatoB1;
import rc.soop.entity.DocumentiDocente;
import rc.soop.entity.Docuserbandi;
import static rc.soop.util.Utility.redirect;
import static rc.soop.util.Utility.zipListFiles;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;
import rc.soop.util.Utility;

/**
 *
 * @author raffaele
 */
public class Download extends HttpServlet {

    //DOWNLOAD classico
    protected void simple(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = request.getParameter("path");
        File downloadFile = new File(filePath);
        String username = request.getSession().getAttribute("username").toString();
        trackingAction(username, "Download File " + downloadFile.getName());
        if (downloadFile.exists()) {
            OutputStream outStream;
            try ( FileInputStream inStream = new FileInputStream(downloadFile)) {
                String mimeType = Files.probeContentType(downloadFile.toPath());
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                response.setContentType(mimeType);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                response.setHeader(headerKey, headerValue);
                outStream = response.getOutputStream();
                byte[] buffer = new byte[4096 * 4096];
                int bytesRead;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
            }
            outStream.close();
        } else {
            redirect(request, response, "page_fnf.html");
        }
    }

    protected void guidabando(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Db_Bando dbb = new Db_Bando();
        String filePath = dbb.getPath("path.manuale.candidato");
        dbb.closeDB();

        File downloadFile = new File(filePath);
        if (downloadFile.exists()) {
            FileInputStream inStream = new FileInputStream(downloadFile);
            String mimeType = Files.probeContentType(downloadFile.toPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096 * 4096];
            int bytesRead = -1;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();
        } else {
            redirect(request, response, "page_fnf.html");
        }
    }

    protected void guidaConvenzioni(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Db_Bando dbb = new Db_Bando();
        String filePath = dbb.getPath("path.manuale.convenzioni");
        dbb.closeDB();
        File downloadFile = new File(filePath);
        if (downloadFile.exists()) {
            FileInputStream inStream = new FileInputStream(downloadFile);
            String mimeType = Files.probeContentType(downloadFile.toPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096 * 4096];
            int bytesRead = -1;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();
        } else {
            redirect(request, response, "page_fnf.html");
        }
    }

    protected void avvisobando(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Db_Bando dbb = new Db_Bando();
        String filePath = dbb.getPath("path.avviso");
        dbb.closeDB();
        File downloadFile = new File(filePath);
        if (downloadFile.exists()) {
            OutputStream outStream;
            try ( FileInputStream inStream = new FileInputStream(downloadFile)) {
                String mimeType = Files.probeContentType(downloadFile.toPath());
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                response.setContentType(mimeType);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                response.setHeader(headerKey, headerValue);
                outStream = response.getOutputStream();
                byte[] buffer = new byte[4096 * 4096];
                int bytesRead = -1;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
            }
            outStream.close();
        } else {
            redirect(request, response, "page_fnf.html");
        }
    }

    protected void viewFileConvenzioni(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getSession().getAttribute("username").toString();
        String codicedoc = request.getParameter("codicedoc");
        String filePath = ActionB.getPathConvenzioni(username, codicedoc);
        File downloadFile = new File(filePath);
        if (downloadFile.exists()) {
            FileInputStream inStream = new FileInputStream(downloadFile);
            String mimeType = Files.probeContentType(downloadFile.toPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096 * 4096];
            int bytesRead = -1;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();
        } else {
            redirect(request, response, "page_fnf.html");
        }
    }

    //download docmento del bando
    protected void docbando(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipodoc = request.getParameter("tipodoc");
        String bandorif = request.getSession().getAttribute("bandorif").toString();
        String username = request.getSession().getAttribute("username").toString();
        Db_Bando dbb = new Db_Bando();
        File downloadFile = downloadFile = new File(dbb.getPathDocUserBando(bandorif, username, tipodoc));
        dbb.closeDB();
        if (downloadFile != null && downloadFile.exists()) {
            trackingAction(request.getSession().getAttribute("username").toString(), "Download File " + downloadFile.getName());
            FileInputStream inStream = new FileInputStream(downloadFile);
            String mimeType = Files.probeContentType(downloadFile.toPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096 * 4096];
            int bytesRead = -1;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();
        } else {
            redirect(request, response, "page_fnf.html");
        }
    }

    protected void allegatobando(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        Db_Bando dbb = new Db_Bando();
        dbb.closeDB();
        File downloadFile = new File(path);
        trackingAction(request.getSession().getAttribute("username").toString(), "Download File " + downloadFile.getName());
        if (downloadFile.exists()) {
            FileInputStream inStream = new FileInputStream(downloadFile);
            String mimeType = Files.probeContentType(downloadFile.toPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096 * 4096];
            int bytesRead = -1;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();
        } else {
            redirect(request, response, "page_fnf.html");
        }
    }

    //download rup
    protected void docbandoconsrup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipodoc = request.getParameter("tipodoc");
        String tipologia = request.getParameter("tipologia");
        String username = request.getParameter("userdoc");
        String note = request.getParameter("note");
        String bandorif = request.getSession().getAttribute("bandorif").toString();
        String path = request.getParameter("path");
        String filePath = "";
        if (path == null || path.trim().equals("")) {
            Db_Bando dbb = new Db_Bando();
            filePath = dbb.getPathDocUserBando(bandorif, username, tipodoc, tipologia, note);
            dbb.closeDB();
        } else {
            filePath = path;
        }

        File downloadFile = new File(filePath);
        trackingAction(request.getSession().getAttribute("username").toString(), "Download File " + downloadFile.getName());
        if (downloadFile.exists()) {
            FileInputStream inStream = new FileInputStream(downloadFile);
            String mimeType = Files.probeContentType(downloadFile.toPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096 * 4096];
            int bytesRead = -1;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();
        } else {
            redirect(request, response, "page_fnf.html");
        }
    }

    protected void docbandoconsConv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("userdoc");
        String path = request.getParameter("path");
        String filePath = "";
        if (path == null || path.trim().equals("")) {
            filePath = ActionB.getConvenzioneROMA(username);
        } else {
            filePath = path;
        }

        File downloadFile = new File(filePath);
        trackingAction(request.getSession().getAttribute("username").toString(), "Download File " + downloadFile.getName());
        if (downloadFile.exists()) {
            FileInputStream inStream = new FileInputStream(downloadFile);
            String mimeType = Files.probeContentType(downloadFile.toPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096 * 4096];
            int bytesRead = -1;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();
        } else {
            redirect(request, response, "page_fnf.html");
        }
    }

    protected void docbandoconsrupB1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = request.getParameter("path");
        File downloadFile = new File(filePath);
        trackingAction(request.getSession().getAttribute("username").toString(), "Download File " + downloadFile.getName());
        if (downloadFile.exists()) {
            FileInputStream inStream = new FileInputStream(downloadFile);
            String mimeType = Files.probeContentType(downloadFile.toPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096 * 4096];
            int bytesRead = -1;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();
        } else {
            redirect(request, response, "page_fnf.html");
        }
    }

    //download modello pdf documento
    protected void modellodoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") == null) {
            redirect(request, response, "home.jsp");
        } else {
            String tipodoc = request.getParameter("tipodoc");
            String username = request.getSession().getAttribute("username").toString();
            String bandorif = request.getSession().getAttribute("bandorif").toString();
            Db_Bando dbb = new Db_Bando();
            String pathtemp = dbb.getPath("pathtemp");
            String filePath = dbb.getPath("path.allegato.C");
            File downloadFile = null;
            dbb.closeDB();
            if (tipodoc.equals("DONL")) {
                downloadFile = domanda(pathtemp, new File(filePath), bandorif, username);
            } else if (tipodoc.equals("DONLA")) {
                // PER SVILUPPO
                filePath = Pdf_new.compileAllegatoA(username, bando, ActionB.getAllegatoA(username));
                if (filePath != null) {
                    downloadFile = new File(filePath);
                }
            } else if (tipodoc.equals("DONLB")) {
                // PER SVILUPPO
                filePath = Pdf_new.compileAllegatoB(username, bando, ActionB.getAllegatoA(username));
                if (filePath != null) {
                    downloadFile = new File(filePath);
                }
            } else {
                downloadFile = new File(filePath);
            }
            if (downloadFile != null && downloadFile.exists()) {
                trackingAction(request.getSession().getAttribute("username").toString(), "Download File (con modello) " + downloadFile.getName());
                FileInputStream inStream = new FileInputStream(downloadFile);
                String mimeType = Files.probeContentType(downloadFile.toPath());
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                response.setContentType(mimeType);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                response.setHeader(headerKey, headerValue);
                OutputStream outStream = response.getOutputStream();
                byte[] buffer = new byte[4096 * 4096];
                int bytesRead = -1;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                inStream.close();
                outStream.close();
            } else {
                redirect(request, response, "page_fnf.html");
            }
        }
    }

    protected void zipdomanda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bandorif = request.getSession().getAttribute("bandorif").toString();
        String username = request.getParameter("userdoc");
        ArrayList<Docuserbandi> lid1 = ActionB.listaDocuserbando(bandorif, username);
        ArrayList<DocumentiDocente> al = ActionB.listaDocUserBandoDocenti(username);
        ArrayList<File> ff = new ArrayList<>();
        for (int i = 0; i < lid1.size(); i++) {
            if (!lid1.get(i).getPath().equals("-")) {
                ff.add(new File(lid1.get(i).getPath()));
            }
        }
        for (int i = 0; i < al.size(); i++) {
            ff.add(new File(al.get(i).getB1()));
            ff.add(new File(al.get(i).getCv()));
            ff.add(new File(al.get(i).getDr()));
        }
        Db_Bando dbb = new Db_Bando();
        String diring = dbb.getPath("pathtemp");
        dbb.closeDB();
        String name = bandorif + username + new DateTime().toString("yyyyMMddHHmmssSSS") + ".zip";
        File zipout = new File(diring + File.separator + name);
        if (zipListFiles(ff, zipout)) {
            FileInputStream inStream = new FileInputStream(zipout);
            String mimeType = Files.probeContentType(zipout.toPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", zipout.getName());
            response.setHeader(headerKey, headerValue);
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096 * 4096];
            int bytesRead = -1;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();
        } else {
            redirect(request, response, "page_fnf.html");
        }
    }

    protected void excelcom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String filePath = "/mnt/mcover/bandoba0i9/bandoi9.xlsx";

        Db_Bando dbb = new Db_Bando();
        String filePath = dbb.getPath("path.result.excel");
        dbb.closeDB();

        File downloadFile = new File(filePath);
        if (downloadFile.exists()) {
            FileInputStream inStream = new FileInputStream(downloadFile);
            String mimeType = Files.probeContentType(downloadFile.toPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096 * 4096];
            int bytesRead = -1;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();
        } else {
            redirect(request, response, "page_fnf.html");
        }
    }

    protected void excelcomParziale(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String data_da = request.getParameter("data_da");
            String data_a = request.getParameter("data_a");
            SimpleDateFormat sdf_in = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // creo l'oggetto
            String dt_a = "";
            String dt_da = "";
            if (!data_a.trim().equals("")) {
                Date a = sdf_in.parse(data_a);
                dt_a = sdf.format(a);
            }
            if (!data_da.trim().equals("")) {
                Date da = sdf_in.parse(data_da);
                dt_da = sdf.format(da);
            }
            Db_Bando db = new Db_Bando();
            String path = db.expexcel_ricerca(dt_da, dt_a);
            db.closeDB();
            File downloadFile = new File(path);
            if (downloadFile.exists()) {
                FileInputStream inStream = new FileInputStream(downloadFile);
                String mimeType = Files.probeContentType(downloadFile.toPath());
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                response.setContentType(mimeType);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                response.setHeader(headerKey, headerValue);
                OutputStream outStream = response.getOutputStream();
                byte[] buffer = new byte[4096 * 4096];
                int bytesRead = -1;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                inStream.close();
                outStream.close();
            } else {
                redirect(request, response, "page_fnf.html");
            }
        } catch (Exception e) {
            trackingAction("service", Utility.estraiEccezione(e));
        }
    }

    protected void downconv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String var = request.getParameter("ctrl");
        String path = "";

        Db_Bando dbb = new Db_Bando();
        if (var.equals("1")) {
            path = dbb.getPath("path.convenzione");
        }
        if (var.equals("2")) {
            path = dbb.getPath("path.convenzione.mod1");
        }
        if (var.equals("3")) {
            path = dbb.getPath("path.convenzione.mod2");
        }
        dbb.closeDB();

        File downloadFile = new File(path);
        if (downloadFile.exists()) {
            FileInputStream inStream = new FileInputStream(downloadFile);
            String mimeType = Files.probeContentType(downloadFile.toPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096 * 4096];
            int bytesRead = -1;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();
        } else {
            redirect(request, response, "page_fnf.html");
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {

        try {

            response.setContentType("text/html;charset=UTF-8");
            String action = request.getParameter("action");
            if (action.equals("guida")) {
                guidabando(request, response);
            }
            if (action.equals("guidaConvenzioni")) {
                guidaConvenzioni(request, response);
            }
            if (action.equals("avviso")) {
                avvisobando(request, response);
            }
            if (request.getSession().getAttribute("username") == null) {
                redirect(request, response, "home.jsp");
            } else {

                if (action.equals("simple")) {
                    simple(request, response);
                }
                if (action.equals("docbando")) {
                    docbando(request, response);
                }
                if (action.equals("docbandoconsrup")) {
                    docbandoconsrup(request, response);
                }
                if (action.equals("docbandoconsrupB1")) {
                    docbandoconsrupB1(request, response);
                }
                if (action.equals("modellodoc")) {
                    modellodoc(request, response);
                }
                if (action.equals("zipdomanda")) {
                    zipdomanda(request, response);
                }
                if (action.equals("allegatobando")) {
                    allegatobando(request, response);
                }
                if (action.equals("docbandoDocente")) {
                    docbandoDocenti(request, response);
                }
                if (action.equals("docAllegatoB1")) {
                    docAllegatoB1(request, response);
                }
                if (action.equals("excelcom")) {
                    excelcom(request, response);
                }
                if (action.equals("excelcomParziale")) {
                    excelcomParziale(request, response);
                }
                if (action.equals("viewFileConvenzioni")) {
                    viewFileConvenzioni(request, response);
                }
                if (action.equals("docbandoconsConv")) {
                    docbandoconsConv(request, response);
                }
                if (action.equals("downconv")) {
                    downconv(request, response);
                }
            }
        } catch (Exception ex) {
            trackingAction("service", Utility.estraiEccezione(ex));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    //download docmento del bando
    protected void docbandoDocenti(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipodoc = request.getParameter("tipodoc");
        String indice = request.getParameter("indice");
        String username = request.getSession().getAttribute("username").toString();
        String pathing = "";
        ArrayList<AllegatoB1> al = ActionB.getAllegatoB1(username, indice);
        for (int i = 0; i < al.size(); i++) {
            if (tipodoc.equals("cv")) {
                pathing = al.get(i).getAllegatocv();
            }
            if (tipodoc.equals("b1")) {
                pathing = al.get(i).getAllegatob1();
            }
            if (tipodoc.equals("ci")) {
                pathing = al.get(i).getAllegatodr();
            }
        }
        Db_Bando dbb = new Db_Bando();
        File downloadFile = null;
        downloadFile = new File(pathing);
        dbb.closeDB();
        if (downloadFile != null && downloadFile.exists()) {
            trackingAction(request.getSession().getAttribute("username").toString(), "Download File " + downloadFile.getName());
            FileInputStream inStream = new FileInputStream(downloadFile);
            String mimeType = Files.probeContentType(downloadFile.toPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096 * 4096];
            int bytesRead = -1;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();
        } else {
            redirect(request, response, "page_fnf.html");
        }
    }

    protected void docAllegatoB1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Utility.printRequest(request);
        String username = request.getSession().getAttribute("username").toString();
        String iddocente = request.getParameter("iddocente");
        String pathing = "";
        String nomeDocente = request.getParameter("nomeDocente");
        File downloadFile = null;
        downloadFile = new File(pathing);
        try {
            trackingAction(request.getSession().getAttribute("username").toString(), "Download File " + downloadFile.getName());
            pathing = Pdf_new.compileAllegatoB1(username, ActionB.alb1(username, iddocente), nomeDocente);
            if (pathing != null) {
                downloadFile = new File(pathing);
            }
            if (downloadFile != null && downloadFile.exists()) {
                trackingAction(request.getSession().getAttribute("username").toString(), "Download File (con modello) " + downloadFile.getName());
                FileInputStream inStream = new FileInputStream(downloadFile);
                String mimeType = Files.probeContentType(downloadFile.toPath());
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                response.setContentType(mimeType);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                response.setHeader(headerKey, headerValue);
                OutputStream outStream = response.getOutputStream();
                byte[] buffer = new byte[4096 * 4096];
                int bytesRead = -1;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                inStream.close();
                outStream.close();
            } else {
                redirect(request, response, "page_fnf.html");
            }
        } catch (Exception e) {
            trackingAction("service", Utility.estraiEccezione(e));
        }
    }

}

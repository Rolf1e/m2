package com.rolfie.netflixcatalogue.controller.servlets;

import com.google.gson.Gson;
import com.rolfie.netflixcatalogue.controller.dto.SerieDTO;
import com.rolfie.netflixcatalogue.database.dao.season.MockedSeasonDAO;
import com.rolfie.netflixcatalogue.database.dao.serie.MockedSerieDAO;
import com.rolfie.netflixcatalogue.service.NetflixService;
import lombok.RequiredArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/series")
@RequiredArgsConstructor
public class NetflixServlet extends HttpServlet {

    private static final String APPLICATION_JSON = "application/json";

    private final NetflixService netflixService;

    public NetflixServlet() {
        this.netflixService = NetflixService.create(MockedSeasonDAO.create(), MockedSerieDAO.create());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final List<SerieDTO> serieDTOS = netflixService.fetchSeries();

        response.setContentType(APPLICATION_JSON);
        response.getWriter().append(new Gson().toJson(serieDTOS));
    }
}

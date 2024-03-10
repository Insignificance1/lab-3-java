package org.example; // Указывает, что класс MainServlet принадлежит пакету org.example.


import jakarta.servlet.ServletException; // Для работы с сервлетами.
import jakarta.servlet.http.*; // Для работы с HTTP запросами и ответами.
import jakarta.servlet.annotation.*; // Для использования аннотаций, таких как @WebServlet.
import service.FileManager; // Импортирует класс FileManager, который используется для работы с файловой системой.
import java.io.*; // Для работы с вводом-выводом (возможно, для обработки файлов).

@WebServlet(urlPatterns = {"/FileExplorer"}) // Определяет, что этот класс является сервлетом и будет доступен по URL-адресу "/FileExplorer".
public class MainServlet extends HttpServlet { // Объявляет класс MainServlet, который наследует класс HttpServlet для обработки HTTP запросов.
    // Этот метод вызывается при поступлении HTTP GET запроса к сервлету.
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        FileManager fileManager = new FileManager(); // Создает объект FileManager для работы с файловой системой.
        // Получает список папок в указанном пути из параметра запроса "path" и сохраняет в массив folders.
        File[] folders = fileManager.allFolders(request.getParameter("path"));
        //Проверяет, не является ли folders null, и если да, то создает пустой массив.
        if (folders == null) {
            folders = new File[0];
        }
        // Получает список файлов в указанном пути из параметра запроса "path" и сохраняет в массив files.
        File[] files = fileManager.allFiles(request.getParameter("path"));
        // Аналогично, проверяет, не является ли files null, и если да, то создаёт пустой массив.
        if (files == null) {
            files = new File[0];
        }

        // Сохраняет массивы folders и files в атрибуты запроса, чтобы их можно было использовать в JSP.
        request.setAttribute("folders", folders);
        request.setAttribute("files", files);
        // Перенаправляет запрос на страницу FileExplorer.jsp, передавая информацию о папках и файлах.
        request.getRequestDispatcher("FileExplorer.jsp").forward(request, response);

    }

    public void destroy() {
    }
}

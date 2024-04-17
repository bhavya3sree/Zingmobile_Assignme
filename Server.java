import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.sql.*;

public class Server {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/data", new DataHandler());
        server.setExecutor(null); 
        server.start();
    }

    static class DataHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                handleGetRequest(exchange);
            } else if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                handlePostRequest(exchange);
            } else if (exchange.getRequestMethod().equalsIgnoreCase("PUT")) {
                handlePutRequest(exchange);
            } else if (exchange.getRequestMethod().equalsIgnoreCase("DELETE")) {
                handleDeleteRequest(exchange);
            } else {
                exchange.sendResponseHeaders(405, -1); 
            }
        }

        private void handleGetRequest(HttpExchange exchange) throws IOException {
            // Handle GET request to read data
            String query = exchange.getRequestURI().getQuery();
            String role = null;
            String collegeId = null;
            String section = null;
            String studentId = null;

            if (query != null) {
                String[] queryParams = query.split("&");
                for (String param : queryParams) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2) {
                        if (keyValue[0].equalsIgnoreCase("role")) {
                            role = keyValue[1];
                        } else if (keyValue[0].equalsIgnoreCase("collegeId")) {
                            collegeId = keyValue[1];
                        } else if (keyValue[0].equalsIgnoreCase("section")) {
                            section = keyValue[1];
                        } else if (keyValue[0].equalsIgnoreCase("studentId")) {
                            studentId = keyValue[1];
                        }
                    }
                }
            }

            if (role == null) {
                exchange.sendResponseHeaders(400, -1); 
                return;
            }

            DatabaseManager dbManager = new DatabaseManager();
            ResultSet resultSet = null;
            try {
                resultSet = dbManager.readData(role, collegeId, section, studentId);
                exchange.sendResponseHeaders(200, 0); 
                OutputStream responseBody = exchange.getResponseBody();
                writeResultSet(responseBody, resultSet);
                responseBody.close();
            } catch (SQLException e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(500, -1);
                dbManager.closeConnection();
            }
        }

        private void writeResultSet(OutputStream outputStream, ResultSet resultSet) throws IOException, SQLException {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    writer.print(resultSet.getString(i));
                    if (i < columnCount) {
                        writer.print(",");
                    }
                }
                writer.println();
            }
            writer.flush();
        }

        private void handlePostRequest(HttpExchange exchange) throws IOException {
             InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder requestData = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                requestData.append(line);
            }
            br.close();
            isr.close();
        }

        private void handlePutRequest(HttpExchange exchange) throws IOException {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder requestData = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                requestData.append(line);
            }
            br.close();
            isr.close();
        }

        private void handleDeleteRequest(HttpExchange exchange) throws IOException {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder requestData = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                requestData.append(line);
            }
            br.close();
            isr.close();
        }
    }
}

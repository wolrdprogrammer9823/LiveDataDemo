package com.jetpack.livedatademo.entities;
import java.util.List;

public class VideoHomeTabBean {

    private int code;

    private String message;

    private List<HomeTabInfo> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HomeTabInfo> getResult() {
        return result;
    }

    public void setResult(List<HomeTabInfo> result) {
        this.result = result;
    }

    public static class HomeTabInfo {
      /*
      * {
            "nameType":0,
            "apiUrl":"http://baobab.kaiyanapp.com/api/v5/index/tab/allRec?page=0",
            "name":"推荐",
            "tabType":0,
            "id":-2,
            "adTrack":null
        },
      * */
        private int nameType;
        private String apiUrl;
        private String name;
        private int tabType;
        private int id;
        private String adTrack;

        public int getNameType() {
            return nameType;
        }

        public void setNameType(int nameType) {
            this.nameType = nameType;
        }

        public String getApiUrl() {
            return apiUrl;
        }

        public void setApiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTabType() {
            return tabType;
        }

        public void setTabType(int tabType) {
            this.tabType = tabType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAdTrack() {
            return adTrack;
        }

        public void setAdTrack(String adTrack) {
            this.adTrack = adTrack;
        }
    }
}

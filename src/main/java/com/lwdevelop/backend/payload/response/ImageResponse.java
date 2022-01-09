package com.lwdevelop.backend.payload.response;

public class ImageResponse {
    private int status;
    private String message;
    private Image image;

    public static class Image {
        private String url;

        public Image(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public ImageResponse(int status, String message, Image image) {
        this.status = status;
        this.message = message;
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

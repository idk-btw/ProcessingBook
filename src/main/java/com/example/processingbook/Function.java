package com.example.processingbook;

/**
 * Клас для роботи з інформацією з бази даних
 */
class Function {
    private String category;
    private String categoryInfo;
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategoryInfo() {
        return categoryInfo;
    }
    public void setCategoryInfo(String categoryInfo) {
        this.categoryInfo = categoryInfo;
    }
    public Function(String category, String categoryInfo) {
        this.category = category;
        this.categoryInfo = categoryInfo;
    }

    /**
     * Для створення екземпляру класу без задавання значень
     */
    public Function(){

    }
}
package com.bowlfullbuddies.bowlfullbuddies.dto;

public class ProductReviewDTO {
    private String reviewerName;
    private String comment;
    private int rating;
    private int helpfulCount;

    public ProductReviewDTO(String reviewerName, String comment, int rating, int helpfulCount) {
        this.reviewerName = reviewerName;
        this.comment = comment;
        this.rating = rating;
        this.helpfulCount = helpfulCount;
    }
    public String getReviewerName() {
        return reviewerName;
    }
    public String getComment() {
        return comment;
    }
    public int getRating() {
        return rating;
    }
    public int getHelpfulCount() {
        return helpfulCount;
    }
    
    
}

interface Styling {
  public static final String OffWhite = "rgba(235, 236, 208, 1)";
  public static final String PaleGreen = "rgba(115, 149, 82, 1)";

  public static final String moveStyle = "-fx-background-color: rgba(0, 0, 0, 0.25); " +
      "-fx-background-radius: 50%; " +
      "-fx-background-insets: 25%;";

  public static final String captureStyle = "-fx-background-color: transparent; " +
      "-fx-border-color: rgba(0, 0, 0, 0.25); " +
      "-fx-border-width: 5px; " +
      "-fx-border-radius: 50%;";

  public static final String castleStyle = "-fx-background-color: transparent; " + // Make the background transparent
      "-fx-border-color: %s; " + // Set the border color
      "-fx-border-width: %f; " + // Set the border width
      "-fx-border-style: solid; " + // Use a solid border
      "-fx-padding: 0; " + // Remove any default padding
      "-fx-shape: \"M %f 0 L %f %f L 0 %f Z\";";

  public static final String checkStyleOffWhite = "rgba(250, 123, 64)";
  public static final String checkStylePaleGreen = "rgba(220, 101, 33)";

  public static final String OffWhiteSelected = "rgba(245, 246, 129, 1)";
  public static final String PaleGreenSelected = "rgba(185, 202, 66, 1)";

  public static final String gameEndAlertStyle = "-fx-background-color: rgba(60, 56, 54, 1)";

  public static final int tileSize = 60;
  public static final int moveSize = 25;
  public static final int captureSize = 70;
}

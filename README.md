# Timefall Shelter Teleporter

# Overview

The Timefall Shelter Teleporter is a Java program designed to manage and select shelters based on user input and availability. It performs the following tasks:

# Input Handling:

Accepts a list of available timefall shelters in JSON format.
Receives comma-separated chiral frequencies supported by the user's wrist cuff.
Shelter Selection:

Searches through the provided list of shelters to find the first one that matches the supported chiral frequencies.
Uses a collection from the java.util.Collection framework to efficiently manage and search for shelters.

# JSON Management:

Prunes unavailable shelters from the JSON data as they are encountered.
Saves the updated list of shelters by overwriting the original JSON file.
Requirements
Java Development Kit (JDK): Ensure you have JDK 8 or higher installed.
JSON Library: This program uses a JSON library for parsing and modifying JSON data. Jackson or Gson are recommended.

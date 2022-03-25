
# OBS TEXT EDITOR

## PROJECT NOTES
- Built with Maven and multiple dependencies
- A war file is when multiple jar files are stacked on top of one another. This was necessary to ensure all dependencies are maintained in the local project without the help of an IDE

## USING THE PROGRAM
- When selecting your JSON path, use the absolute path INCLUDING "Esports2021.json" at the end. The path should fall under %appdata% --> obs-studio --> basic --> scenes 
- OBS cannot be open while trying to click the green "update stream" button. OBS will rewrite the JSON file back to its original contents and nothing will change 
- Consequently, restarting OBS when the text does not update is necessary since OBS will attempt to rewrite the configuration file 
- OBS Setup: 
    - JSON files must be the same for the correct sources to be changed
    - Please refer to my obs-studio profile in the drive for the appropriate OBS JSON configuration file

## RUNNING THE JAR
- Build all dependencies by navigating to the topmost level of the war_build folder. Type in cmd:
'''
build.bat
'''
- Run the GUI. Type in cmd:
'''
gui.bat
'''


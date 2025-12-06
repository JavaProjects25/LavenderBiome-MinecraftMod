# LavenderBiome-MinecraftMod ![Java](https://img.shields.io/badge/Language-Java-red?logo=java&logoColor=white)

**Project Document:** [Google Doc Link](https://docs.google.com/document/d/1EFNs3szehrvj0eT7-y4afhu91Br_vpwuPW4TIQy5bZs/edit?tab=t.0)
> A Minecraft Mod developed in Java for the Java Final Project.

---

## 📚 About the Project

**LavenderBiome** is a custom mod built for Minecraft 1.21.10 using the Fabric Loader. This mod was created as part of a final project for our Java course, demonstrating **object-oriented programming**, **design patterns**, **teamwork**, and **Java principles** in a game development context.

**Key Features:**
- **New Biome:** The **Lavender Biome**, featuring custom generation and purple/pink sheep variants.
- **Custom Items:** **Lavendrite Ingot**, **Lavender Latte** (consumable giving Haste), and the **Crosspick** (a tool that mines a 5 block area in a + sign formation).
- **New Blocks:** **Lavenderwood** tree set (logs, planks, leaves) and **Lavendrite Ore**.
- **Localization:** Localization into English, French, and Spanish.

## 📌 Requirements

To install this project, please ensure you have:
- **IntelliJ IDEA** installed.
- **Java Development Kit (JDK) 21** installed.
- **Git** installed on your computer.

## 💡Running Instructions (Step-by-Step)

Please follow these exact steps to download and run the project.

### Step 1: Download the Project to your Desktop
1.  Open your command prompt (Windows) or Terminal (Mac/Linux).
2.  Type the following command to go to your Desktop and press **Enter**:
    ```bash
    cd Desktop
    ```
3.  Type this command to download the project and press **Enter**:
    ```bash
    git clone https://github.com/JavaProjects25/LavenderBiome-MinecraftMod.git
    ```
4.  You should now see a folder named `LavenderBiome-MinecraftMod` on your Desktop.

### Step 2: Open in IntelliJ IDEA
1.  Launch **IntelliJ IDEA**.
2.  Click **Open** (top center)
3.  Navigate to your **Desktop**, select the `LavenderBiome-MinecraftMod` folder, and click **Select Folder** (bottom right).
4.  Check **Add IDE and 'LavenderBiome-Min...' folders to the Microsoft Defender exclusions list.
5.  Click **Trust Project**
6.  Click **Allow** if an elevator.exe prompt shows up.
7.  **Wait** a moment. IntelliJ will start scanning the files (you will see a progress bar at the bottom right). Let it finish completely.

### Step 3: Prepare the Code
1.  Look for the **Gradle** tab on the very right-hand side of the IntelliJ window (it has an elephant icon) and click it.
2.  Open the folder list: **LavenderBiome** -> **Tasks** -> **fabric**.
3.  Find the task named **`genSources`** and **double-click** it.
4.  **Wait** for it to finish. This downloads the Minecraft source code so the mod works correctly. This may take a while.

### Step 4: Run the Game
1.  In that same Gradle list (**Tasks** -> **fabric**), find the task named **`runClient`**.
2.  **Double-click** it.
3.  The Minecraft game window will pop up.

### Step 5: Test In-Game
1.  Click **Singleplayer**.
2.  Click **Game Mode: Survival** twice until it toggles to say **Game Mode: Creative**.
3.  Click **Create New World** (bottom left).
4. Wait for the world to load.
5.  Once in the game, press **E** to open your inventory.
6.  Click the arrow `>` on the top right of the menu to go to the second page and find the **Lavender Biome** tab to see our items!
7. Press **ESC** to close the inventory menu.
8. Press **T** to open the chat.
9. Paste (**CTRL + V**) the following command: 
`/locate biome lavenderbiome:lavenderbiome`.
10. Press **ENTER** to print the coordinates to the nearest Lavender Biome.
11. Press **T** to open the chat again.
12. Mouse over the printed coordinates in green and **Left Click** on them to paste the command in the chatbox.
13. Press **ENTER** to teleport to those coordinates. ***WARNING:*** *You will likely spawn underground. This is a limitation of how the `/locate` command works in Minecraft.* ***Left Click*** *to destroy blocks.* ***Double tap SPACEBAR*** *to fly up.*

## 🎮 Controls
Movement:
- **WASD**: Move forward, left, backwards, right.
- **Move mouse**: Look around.
- **Double tap SPACEBAR [on ground]**: Activate fly mode.
- **Hold SPACEBAR [in air]**: Fly up.
- **Hold SHIFT [in air]**: Fly down.
- **Left Click**: Break blocks.
- **Right Click**: Use item. *This behaviour may change when holding a tool.*

Menus:
- **Mouse Scroll**: Scroll selected item in hotbar.
- **E**: Open inventory.
- **Left Click [on an item in inventory]**: Picks up the item to your cursor.
- **Left Click [on a slot in inventory]**: Places the item from your cursor to the inventory slot.
- **ESC**: Close any menu / Open pause menu
- **T**: Open chat bar.
- **ENTER [with chat bar open]**: Send chat.

## ✍️ Authors
- **Santiago Santos** [@SantySantos](https://www.github.com/SantySantos)
- **Marcel Taveau** [@MarcelTaveau](https://www.github.com/MarcelTaveau)

## 📌 Notes
- The project uses Gradle 9.1.0.
- Dependencies include `fabric-api` and `TerraBlender`.
- Feel free to fork and add your own changes!

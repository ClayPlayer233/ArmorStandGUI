package me.clayplayer.armorstandgui.Utils;

import me.clayplayer.armorstandgui.ArmorStandGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GuiUtils {

    private final ArmorStandGUI plugin;

    public ArrayList<String> armsLore = new ArrayList<>();
    public ArrayList<String> glowLore = new ArrayList<>();
    public ArrayList<String> baseLore = new ArrayList<>();

    public ItemStack head = new ItemStack(Material.DIAMOND_HELMET);
    public ItemStack body = new ItemStack(Material.DIAMOND_CHESTPLATE);
    public ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS);
    public ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);

    public ItemMeta headMeta;
    public ItemMeta bodyMeta;
    public ItemMeta legsMeta;
    public ItemMeta bootsMeta;

    public ArrayList<String> headLore = new ArrayList<>();
    public ArrayList<String> bodyLore = new ArrayList<>();
    public ArrayList<String> legsLore = new ArrayList<>();
    public ArrayList<String> bootsLore = new ArrayList<>();

    public GuiUtils(ArmorStandGUI plugin) {
        this.plugin = plugin;
        armsLore.add(ChatColor.GRAY + "未添加手臂");
        glowLore.add(ChatColor.GRAY + "未添加发光");
        baseLore.add(ChatColor.GRAY + "未添加基座");

        this.headMeta = this.head.getItemMeta();
        this.bodyMeta = this.body.getItemMeta();
        this.legsMeta = this.legs.getItemMeta();
        this.bootsMeta = this.boots.getItemMeta();

        this.headMeta.setDisplayName(ChatColor.GRAY + "头盔未设置");
        this.bodyMeta.setDisplayName(ChatColor.GRAY + "护甲未设置");
        this.legsMeta.setDisplayName(ChatColor.GRAY + "护腿未设置");
        this.bootsMeta.setDisplayName(ChatColor.GRAY + "靴子未设置");
    }

    public void ArmorStandGUI(Player p) {
        //主菜单窗口
        Inventory main_menu = Bukkit.createInventory(p, 9, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ArmorStandGUI-Title")));

        //创建盔甲架按钮
        ItemStack create = new ItemStack(Material.ARMOR_STAND);
        ItemMeta createMeta = create.getItemMeta();
        ArrayList<String> createLore = new ArrayList<>();
        createLore.add(ChatColor.GRAY + "创建一个新的盔甲架");
        createMeta.setLore(createLore);
        createMeta.setDisplayName(ChatColor.WHITE + "创建盔甲架");
        create.setItemMeta(createMeta);

        //关闭盔甲架GUI按钮
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        ArrayList<String> closeLore = new ArrayList<>();
        closeLore.add(ChatColor.GRAY + "关闭菜单");
        closeMeta.setLore(closeLore);
        closeMeta.setDisplayName(ChatColor.WHITE + "关闭菜单");
        close.setItemMeta(closeMeta);

        main_menu.setItem(0, create);
        main_menu.setItem(8, close);

        p.openInventory(main_menu);
    }

    public void createArmorStandGUI(Player p) {
        //创建盔甲架窗口
        Inventory create_menu = Bukkit.createInventory(p, 9, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("createArmorStandGUI-Title")));

        ItemStack arms = new ItemStack(Material.ARMOR_STAND);
        ItemStack glow = new ItemStack(Material.BEACON);
        ItemStack armor = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack base = new ItemStack(Material.STONE_SLAB);
        ItemStack complete = new ItemStack(Material.GREEN_WOOL);
        ItemStack cancel = new ItemStack(Material.RED_WOOL);

        ItemMeta arms_meta = arms.getItemMeta();
        ItemMeta glow_meta = glow.getItemMeta();
        ItemMeta armor_meta = armor.getItemMeta();
        ItemMeta base_meta = base.getItemMeta();
        ItemMeta complete_meta = complete.getItemMeta();
        ItemMeta cancel_meta = cancel.getItemMeta();

        arms_meta.setDisplayName(ChatColor.YELLOW + "手臂");
        glow_meta.setDisplayName("发光");
        armor_meta.setDisplayName(ChatColor.AQUA + "装备");
        base_meta.setDisplayName(ChatColor.GOLD + "基座");
        complete_meta.setDisplayName(ChatColor.GREEN + "确定并创建");
        cancel_meta.setDisplayName(ChatColor.RED + "取消创建");

        arms_meta.setLore(armsLore);
        glow_meta.setLore(glowLore);
        base_meta.setLore(baseLore);

        arms.setItemMeta(arms_meta);
        glow.setItemMeta(glow_meta);
        armor.setItemMeta(armor_meta);
        base.setItemMeta(base_meta);
        complete.setItemMeta(complete_meta);
        cancel.setItemMeta(cancel_meta);

        create_menu.addItem(arms, glow, armor, base);
        create_menu.setItem(7, complete);
        create_menu.setItem(8, cancel);

        p.openInventory(create_menu);

    }

    public void openConfirmMenu(Player p, Material option) {
        //创建确定窗口
        Inventory confirm_menu = Bukkit.createInventory(p, 36, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ConfirmGUI-Title")));

        //获取确定物品
        ItemStack option_item = new ItemStack(option);
        ItemMeta option_meta = option_item.getItemMeta();

        //检测确定物品
        if (option.equals(Material.ARMOR_STAND)) {
            option_meta.setDisplayName("是否添加手臂？");
            option_item.setItemMeta(option_meta);
        } else if (option.equals(Material.BEACON)) {
            option_meta.setDisplayName("是否发光？");
            option_item.setItemMeta(option_meta);
        } else if (option.equals(Material.STONE_SLAB)) {
            option_meta.setDisplayName("是否添加基座？");
            option_item.setItemMeta(option_meta);
        } else {
            p.sendMessage(ChatColor.RED + "未知的确定参数！");
            plugin.getLogger().info(ChatColor.RED + "错误：未知的确定参数！");
            return;
        }

        //创建确定和取消按钮
        ItemStack Yes = new ItemStack(Material.GREEN_WOOL);
        ItemMeta yesMeta = Yes.getItemMeta();
        yesMeta.setDisplayName(ChatColor.GREEN + "确定");
        Yes.setItemMeta(yesMeta);
        ItemStack No = new ItemStack(Material.RED_WOOL);
        ItemMeta noMeta = No.getItemMeta();
        noMeta.setDisplayName(ChatColor.RED + "取消");
        No.setItemMeta(noMeta);

        //绘制确定界面
        confirm_menu.setItem(13, option_item);
        confirm_menu.setItem(21, Yes);
        confirm_menu.setItem(23, No);

        p.openInventory(confirm_menu);
    }

    public void openArmorMenu(Player p) {
        //创建装备窗口
        Inventory armor = Bukkit.createInventory(p, 45, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ArmorMenu-Title")));

        headMeta.setLore(headLore);
        bodyMeta.setLore(bodyLore);
        legsMeta.setLore(legsLore);
        bootsMeta.setLore(bootsLore);

        head.setItemMeta(headMeta);
        body.setItemMeta(bodyMeta);
        legs.setItemMeta(legsMeta);
        boots.setItemMeta(bootsMeta);

        //创建完成按钮
        ItemStack Yes = new ItemStack(Material.GREEN_WOOL);
        ItemMeta yesMeta = Yes.getItemMeta();
        yesMeta.setDisplayName(ChatColor.GREEN + "完成");
        Yes.setItemMeta(yesMeta);

        //绘制GUI
        armor.setItem(11, head);
        armor.setItem(12, body);
        armor.setItem(14, legs);
        armor.setItem(15, boots);
        armor.setItem(40, Yes);

        p.openInventory(armor);
    }

}

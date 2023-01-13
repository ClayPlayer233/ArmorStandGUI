package me.clayplayer.armorstandgui.listeners;

import me.clayplayer.armorstandgui.ArmorStandGUI;
import me.clayplayer.armorstandgui.Utils.GuiUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MenuHandler implements Listener {


    private final ArmorStandGUI plugin;
    private final GuiUtils utils;

    public MenuHandler(ArmorStandGUI plugin) {
        this.plugin = plugin;
        this.utils = new GuiUtils(plugin);
    }

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent e) {
        Player p = Bukkit.getPlayerExact(e.getWhoClicked().getName());
        if (e.getCurrentItem() != null) {
            if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ArmorStandGUI-Title")))) {
                if (e.getCurrentItem().getType().equals(Material.ARMOR_STAND)) {
                    utils.createArmorStandGUI(p);
                } else if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
                    p.closeInventory();
                }
            } else if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("createArmorStandGUI-Title")))) {
                if (!plugin.armorStandHashMap.containsKey(p)) {
                    ArmorStand armorStand = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
                    armorStand.setVisible(false);
                    armorStand.setBasePlate(false);
                    plugin.armorStandHashMap.put(p, armorStand);
                } else {
                    ArmorStand armorStand = plugin.armorStandHashMap.get(p);
                    armorStand.teleport(p.getLocation());
                }
                switch (e.getCurrentItem().getType()) {
                    case ARMOR_STAND:
                        utils.openConfirmMenu(p, Material.ARMOR_STAND);
                        break;
                    case BEACON:
                        utils.openConfirmMenu(p, Material.BEACON);
                        break;
                    case LEATHER_CHESTPLATE:
                        utils.openArmorMenu(p);
                        break;
                    case STONE_SLAB:
                        utils.openConfirmMenu(p, Material.STONE_SLAB);
                        break;
                    case GREEN_WOOL:
                        if (plugin.armorStandHashMap.containsKey(p)) {
                            ArmorStand armorStand = plugin.armorStandHashMap.get(p);
                            armorStand.setVisible(true);
                            if (plugin.glow) {
                                armorStand.setGlowing(true);
                            }
                            plugin.glow = false;
                            plugin.armorStandHashMap.remove(p);
                        } else {
                            p.sendMessage(ChatColor.RED + "错误：没有找到玩家对象！");
                            plugin.getLogger().info(ChatColor.RED + "错误：没有找到玩家对象！");
                        }
                        utils.armsLore.set(0, ChatColor.GRAY + "未添加手臂");
                        utils.glowLore.set(0, ChatColor.GRAY + "未添加发光");
                        utils.baseLore.set(0, ChatColor.GRAY + "未添加基座");
                        e.setCancelled(true);
                        p.closeInventory();
                        break;
                    case RED_WOOL:
                        if (plugin.armorStandHashMap.containsKey(p)) {
                            ArmorStand armorStand = plugin.armorStandHashMap.get(p);
                            armorStand.remove();
                            plugin.armorStandHashMap.remove(p);
                        } else {
                            p.sendMessage(ChatColor.RED + "错误：没有找到玩家对象！");
                            plugin.getLogger().info(ChatColor.RED + "错误：没有找到玩家对象！");
                        }
                        utils.armsLore.set(0, ChatColor.GRAY + "未添加手臂");
                        utils.glowLore.set(0, ChatColor.GRAY + "未添加发光");
                        utils.baseLore.set(0, ChatColor.GRAY + "未添加基座");
                        e.setCancelled(true);
                        p.closeInventory();
                        break;
                }
            } else if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ConfirmGUI-Title")))) {
                if (e.getClickedInventory().contains(Material.ARMOR_STAND)) {
                    if (e.getCurrentItem().getType().equals(Material.GREEN_WOOL)) {
                        if (plugin.armorStandHashMap.containsKey(p)) {
                            ArmorStand armorStand = plugin.armorStandHashMap.get(p);
                            armorStand.setArms(true);
                            utils.armsLore.set(0, ChatColor.WHITE + "已添加手臂");
                        } else {
                            p.sendMessage(ChatColor.RED + "错误：没有找到玩家对象！");
                            plugin.getLogger().info(ChatColor.RED + "错误：没有找到玩家对象！");
                        }
                        utils.createArmorStandGUI(p);
                    } else if (e.getCurrentItem().getType().equals(Material.RED_WOOL)) {
                        if (plugin.armorStandHashMap.containsKey(p)) {
                            ArmorStand armorStand = plugin.armorStandHashMap.get(p);
                            armorStand.setArms(false);
                            utils.armsLore.set(0, ChatColor.GRAY + "未添加手臂");
                        } else {
                            p.sendMessage(ChatColor.RED + "错误：没有找到玩家对象！");
                            plugin.getLogger().info(ChatColor.RED + "错误：没有找到玩家对象！");
                        }
                        utils.createArmorStandGUI(p);
                    }
                } else if (e.getClickedInventory().contains(Material.BEACON)) {
                    if (e.getCurrentItem().getType().equals(Material.GREEN_WOOL)) {
                        if (plugin.armorStandHashMap.containsKey(p)) {
                            plugin.glow = true;
                            utils.glowLore.set(0, ChatColor.WHITE + "已添加发光");
                        } else {
                            p.sendMessage(ChatColor.RED + "错误：没有找到玩家对象！");
                            plugin.getLogger().info(ChatColor.RED + "错误：没有找到玩家对象！");
                        }
                        utils.createArmorStandGUI(p);
                    } else if (e.getCurrentItem().getType().equals(Material.RED_WOOL)) {
                        if (plugin.armorStandHashMap.containsKey(p)) {
                            plugin.glow = false;
                            utils.glowLore.set(0, ChatColor.GRAY + "未添加发光");
                        } else {
                            p.sendMessage(ChatColor.RED + "错误：没有找到玩家对象！");
                            plugin.getLogger().info(ChatColor.RED + "错误：没有找到玩家对象！");
                        }
                        utils.createArmorStandGUI(p);
                    }
                } else if (e.getClickedInventory().contains(Material.STONE_SLAB)) {
                    if (e.getCurrentItem().getType().equals(Material.GREEN_WOOL)) {
                        if (plugin.armorStandHashMap.containsKey(p)) {
                            ArmorStand armorStand = plugin.armorStandHashMap.get(p);
                            armorStand.setBasePlate(true);
                            utils.baseLore.set(0, ChatColor.WHITE + "已添加基座");
                        } else {
                            p.sendMessage(ChatColor.RED + "错误：没有找到玩家对象！");
                            plugin.getLogger().info(ChatColor.RED + "错误：没有找到玩家对象！");
                        }
                        utils.createArmorStandGUI(p);
                    } else if (e.getCurrentItem().getType().equals(Material.RED_WOOL)) {
                        if (plugin.armorStandHashMap.containsKey(p)) {
                            ArmorStand armorStand = plugin.armorStandHashMap.get(p);
                            armorStand.setBasePlate(false);
                            utils.baseLore.set(0, ChatColor.GRAY + "未添加基座");
                        } else {
                            p.sendMessage(ChatColor.RED + "错误：没有找到玩家对象！");
                            plugin.getLogger().info(ChatColor.RED + "错误：没有找到玩家对象！");
                        }
                        utils.createArmorStandGUI(p);
                    }
                } else {
                    return;
                }
            } else if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ArmorMenu-Title")))) {
                if (plugin.armorStandHashMap.containsKey(p)) {
                    ArmorStand armorStand = plugin.armorStandHashMap.get(p);
                    switch (e.getCurrentItem().getType()) {
                        case DIAMOND_HELMET:
                            if (armorStand.getHelmet().getType().equals(Material.DIAMOND_HELMET)) {
                                armorStand.setHelmet(null);
                                utils.headMeta.setDisplayName(ChatColor.GRAY + "头盔未设置");
                                utils.openArmorMenu(p);
                            } else {
                                armorStand.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                                utils.headMeta.setDisplayName(ChatColor.WHITE + "头盔已设置");
                                utils.openArmorMenu(p);
                            }
                            break;
                        case DIAMOND_CHESTPLATE:
                            if (armorStand.getChestplate().getType().equals(Material.DIAMOND_CHESTPLATE)) {
                                armorStand.setChestplate(null);
                                utils.bodyMeta.setDisplayName(ChatColor.GRAY + "护甲未设置");
                                utils.openArmorMenu(p);
                            } else {
                                armorStand.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                                utils.bodyMeta.setDisplayName(ChatColor.WHITE + "护甲已设置");
                                utils.openArmorMenu(p);
                            }
                            break;
                        case DIAMOND_LEGGINGS:
                            if (armorStand.getLeggings().getType().equals(Material.DIAMOND_LEGGINGS)) {
                                armorStand.setLeggings(null);
                                utils.legsMeta.setDisplayName(ChatColor.GRAY + "护腿未设置");
                                utils.openArmorMenu(p);
                            } else {
                                armorStand.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                                utils.legsMeta.setDisplayName(ChatColor.WHITE + "护腿已设置");
                                utils.openArmorMenu(p);
                            }
                            break;
                        case DIAMOND_BOOTS:
                            if (armorStand.getBoots().getType().equals(Material.DIAMOND_BOOTS)) {
                                armorStand.setBoots(null);
                                utils.bootsMeta.setDisplayName(ChatColor.GRAY + "靴子未设置");
                                utils.openArmorMenu(p);
                            } else {
                                armorStand.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                                utils.bootsMeta.setDisplayName(ChatColor.WHITE + "靴子已设置");
                                utils.openArmorMenu(p);
                            }
                            break;
                        case GREEN_WOOL:
                            utils.createArmorStandGUI(p);
                            break;
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "错误：没有找到玩家对象！");
                    plugin.getLogger().info(ChatColor.RED + "错误：没有找到玩家对象！");
                }
            } else {
                return;
            }
            e.setCancelled(true);
        }
    }
}

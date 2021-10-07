package xyz.raitaki.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class NMSArmorStand {

    private Location loc;
    private boolean small = false;
    private boolean visible = false;
    private boolean marker = false;
    private boolean namevisible = false;
    private boolean arms = false;
    private Vector3f headrotation =  new Vector3f(0,0,0);
    private Vector3f leftarmrotation =  new Vector3f(0,0,0);
    private Vector3f rightarmrotation =  new Vector3f(0,0,0);
    private Vector3f leftlegrotation =  new Vector3f(0,0,0);
    private Vector3f rightlegrotation =  new Vector3f(0,0,0);
    private Vector3f bodyrotation =  new Vector3f(0,0,0);
    private EntityArmorStand stand;
    private String name = "";
    private List<Pair<EnumItemSlot, ItemStack>> equipment = new ArrayList<>();
    private org.bukkit.inventory.ItemStack item;


    public NMSArmorStand(Location loc) {
        this.loc = loc;
    }

    public void setSmall(boolean b){
        this.small = b;
    }

    public void setInvisible(boolean b){
        this.visible = b;
    }

    public void setMarker(boolean b){
        this.marker = b;
    }

    public void setheadRotation(Vector3f rotation){
        this.headrotation = rotation;
    }

    public void setLeftarmrotation(Vector3f rotation){
        this.leftarmrotation = rotation;
    }

    public void setRightarmrotation(Vector3f rotation){
        this.rightarmrotation = rotation;
    }

    public void setLeftlegrotation(Vector3f rotation){
        this.leftlegrotation = rotation;
    }

    public void setRightlegrotation(Vector3f rotation){
        this.rightlegrotation = rotation;
    }

    public void setBodyrotation(Vector3f rotation){
        this.bodyrotation = rotation;
    }

    public void setCustomNameVisible(boolean b){
        this.namevisible = b;
    }

    public void setCustomName(String s){
        this.name = s;
    }

    public void setArms(boolean b){
     this.arms = b;
    }

    public void setEquipment(String slot, org.bukkit.inventory.ItemStack item){
        this.item = item;

        if(EnumItemSlot.fromName(slot) == null) return;
        if(CraftItemStack.asNMSCopy(item) == null) return;

        equipment.add(new Pair<EnumItemSlot, ItemStack>(EnumItemSlot.fromName(slot), CraftItemStack.asNMSCopy(item)));
    }

    public boolean getArms(){
        return arms;
    }

    public boolean isVisible(){
        return visible;
    }

    public boolean isSmall(){
        return small;
    }

    public boolean getMarker(){
        return marker;
    }

    public boolean isNamevisible(){
        return namevisible;
    }

    public String getName(){
        return name;
    }

    public Vector3f getHeadrotation(){
        return headrotation;
    }

    public Vector3f getLeftarmrotation(){
        return leftarmrotation;
    }

    public Vector3f getRightarmrotation() {
        return rightarmrotation;
    }

    public Vector3f getLeftlegrotation() {
        return leftlegrotation;
    }

    public Vector3f getRightlegrotation() {
        return rightlegrotation;
    }

    public Vector3f getBodyrotation() {
        return bodyrotation;
    }

    public EntityArmorStand getStand(){
        return stand;
    }

    public List<Pair<EnumItemSlot, ItemStack>> getEquipments(){
        return equipment;
    }

    public void spawn(){
        stand = new EntityArmorStand(((CraftWorld) loc.getWorld()).getHandle(), loc.getX(), loc.getY(), loc.getZ());
        stand.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());


        stand.setSmall(small);
        stand.setMarker(marker);
        stand.setInvisible(visible);
        stand.setArms(arms);

        stand.setHeadPose(headrotation);
        stand.setLeftArmPose(leftarmrotation);
        stand.setRightArmPose(rightarmrotation);
        stand.setLeftLegPose(leftlegrotation);
        stand.setRightLegPose(rightlegrotation);
        stand.setBodyPose(bodyrotation);


        stand.setCustomNameVisible(namevisible);
        stand.setCustomName(new ChatComponentText(this.name));
    }


    public void display(Location loc, Double radius){
        PacketPlayOutSpawnEntityLiving spawnPacket = new PacketPlayOutSpawnEntityLiving(stand);
        PacketPlayOutEntityMetadata metadataPacket = new PacketPlayOutEntityMetadata(stand.getId(), stand.getDataWatcher(),true);
        PacketPlayOutEntityEquipment equipmentPacket = new PacketPlayOutEntityEquipment(stand.getId(), equipment);

        if(radius > 0){
            for(Entity entity : loc.getWorld().getNearbyEntities(loc, radius, radius, radius)){
                if(entity instanceof Player){
                    ((CraftPlayer) entity).getHandle().playerConnection.sendPacket(spawnPacket);
                    ((CraftPlayer) entity).getHandle().playerConnection.sendPacket(metadataPacket);
                    if(item != null) {
                        ((CraftPlayer) entity).getHandle().playerConnection.sendPacket(equipmentPacket);
                    }
                }
            }
        }
        else{
            for(Player entity : Bukkit.getOnlinePlayers()){
                ((CraftPlayer) entity).getHandle().playerConnection.sendPacket(spawnPacket);
                ((CraftPlayer) entity).getHandle().playerConnection.sendPacket(metadataPacket);
                if(item !=null) {
                    ((CraftPlayer) entity).getHandle().playerConnection.sendPacket(equipmentPacket);
                }
            }
        }
    }

    public void teleport(Location loc, Location radiusloc, Double radius){
        this.loc = loc;
        stand.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        PacketPlayOutEntityTeleport teleportPacket = new PacketPlayOutEntityTeleport(stand);
        if(radius>0){
            for (Entity viewer : radiusloc.getWorld().getNearbyEntities(radiusloc, radius, radius, radius)) {
                if(viewer instanceof Player){
                    ((CraftPlayer) viewer).getHandle().playerConnection.sendPacket(teleportPacket);
                }
            }
        }
        else {
            for (Player viewer : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) viewer).getHandle().playerConnection.sendPacket(teleportPacket);
            }
        }
    }
}

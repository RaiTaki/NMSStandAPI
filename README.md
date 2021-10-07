# NMSStandAPI



Tested on 1.16.5


EXAMPLE:
```
  NMSArmorStand stand = new NMSArmorStand(p.getLocation()); // Spawn location
  stand.setInvisible(false); 
  stand.setArms(true);
  stand.setInvisible(true);
  stand.setMarker(true);
  stand.setSmall(true);
  stand.setCustomName("aaa");
  stand.setCustomNameVisible(true);


  stand.setBodyrotation(new Vector3f(50,50,50)); // Rotation
  stand.setheadRotation(new Vector3f(50,50,50));
  stand.setLeftarmrotation(new Vector3f(50,50,50));
  stand.setLeftlegrotation(new Vector3f(50,50,50));
  stand.setRightarmrotation(new Vector3f(50,50,50));
  stand.setRightlegrotation(new Vector3f(50,50,50));


  stand.spawn(); //Spawns stand
  stand.display(p.getLocation(), 0D); // Displays around that location, if radius is 0 sends packets to all players if radius > 0, sends to players in radius
  ```

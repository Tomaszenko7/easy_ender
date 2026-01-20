## v1.2.1

### Fixes
- Reduced ore Model Complexity by removing internal faces (performance).
- Clamped ore Model bounds within 0.16 (performance).
- Added cull-face to boundary faces of ore Models (performance).
- Minified ore Model JSON (performance).
- Changed Ender Fragment TeleportTarget method to only teleport to already loaded chunks, vastly improving teleport performance.
- Fixed Ender Fragment Teleport in Nether so you no longer end up on the Bedrock ceiling!

_________________________

## v1.2.0 – Version 1.2 Release!

### Added

- Added 'Ender Slurry', use create crushing wheels to crush Ender Ore into slurry which can then be washed to create x2 Ender Dust.
- Added "Into the thick of it" advancement for obtaining slurry
- Added 'Blaze Ore', can be found between levels 8 and bedrock in the overworld, and more commonly in the nether. Nether Blaze Ore has a chance to drop x2 fragments.
  - Drops Blaze fragments
  - Fire crackle sound
  - Fire particles
  - Drops 0.4xp
  - Can be silk touched to smelt/blast (vanilla) or crushed in Create's crushing wheel to create Blaze Slurry.
- Added 'Blaze Dust', these can be smelted/blasted into Blaze Fragments, much the same as Ender Dust.
  - Cannot be created in vanilla, only by using Create's milling/crushing function.
- Added 'Blaze Fragments', these can be crafted 3x3 into Blaze Powder, much the same as Ender Fragments.
- Added 'Blaze Slurry', use create crushing wheels to crush Blaze Ore into slurry which can then be washed to create x2 Blaze Dust (which can be smelted/blasted) - doubling ore output.
- Added new advancements for Blaze mechanics.
- Added relevant tooltips to all new items
- Added new 3d models for Ore's (they're now prettier!)
- Added ability to craft Eyes of Ender using 8x Ender Fragments and 1x Blaze Powder.
- Added GitHub/CurseForge Links to Fabric Metadata

### Fixes

- Fixed some advancement triggers to allow for new Blaze mechanics advancements.
- Moved all tooltips and names into language file and moved to keys for item tooltips to allow for easier additions in the future.
- Asset/folder clean-up.
- Class clean-up/organise.

### Balances

- Removed crushing Ender Ores into x2 Ender Dust, instead it now creates Ender Slurry that can be washed into Ender Dust.
- Added Blaze mechanics (see added section).
- Tweaked spawn rate of Ender Ore ever so slightly.

## Preference

- Removed glow from Ender ore's as I felt it unnecessary as is included in Blaze ore now which logically should glow because it's hot? yeah?
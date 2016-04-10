package refinedstorage.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class ItemPattern extends ItemBase {
    public static final String NBT_RESULT = "Result";

    public ItemPattern() {
        super("pattern");
    }

    @Override
    public void addInformation(ItemStack pattern, EntityPlayer player, List list, boolean b) {
        if (hasResult(pattern)) {
            list.add(getResult(pattern).getDisplayName());
        }
    }

    public static void setResult(ItemStack pattern, ItemStack stack) {
        if (pattern.getTagCompound() == null) {
            pattern.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound stackTag = new NBTTagCompound();
        stack.writeToNBT(stackTag);

        pattern.getTagCompound().setTag(NBT_RESULT, stackTag);
    }

    public static boolean hasResult(ItemStack pattern) {
        if (pattern.getTagCompound() == null) {
            return false;
        }

        return pattern.getTagCompound().hasKey(NBT_RESULT);
    }

    public static ItemStack getResult(ItemStack pattern) {
        if (!hasResult(pattern)) {
            return null;
        }

        return ItemStack.loadItemStackFromNBT(pattern.getTagCompound().getCompoundTag(NBT_RESULT));
    }
}

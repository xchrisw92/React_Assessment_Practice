import Item from "../Item/Item";

const Items = ({items, handleDeleteItem, handleUpdateItem, handleCompletedUpdate}) => {

    return (items.map((item) => {
                return (
                    <Item
                        className='justify-center'
                        key={item.id}
                        item={item}
                        handleDeleteItem={handleDeleteItem}
                        handleUpdateItem={handleUpdateItem}
                        handleCompletedUpdate={handleCompletedUpdate}
                    />
                )
            }
        )
    )
}
export default Items;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlantThings : MonoBehaviour
{
	public GameObject buildMenu;
	public GameObject muDig;

	public bool spaceToDig;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
		if(Vector3.Distance(transform.position,MCP.player.transform.position) > 3)
			spaceToDig = false;

		if (Input.GetButtonDown("Swing") && spaceToDig)
		{
			MCP.player.SetActive(false);
			muDig.SetActive(true);
			muDig.GetComponent<Animator>().SetTrigger("Dig");
			buildMenu.SetActive(true);
			MCP.player.GetComponent<PlayerControls>().canControl = false;
		}
    }

	private void OnTriggerEnter2D(Collider2D collision)
	{
		if (collision.gameObject.tag == "Player")
			spaceToDig = true;
	}

	private void OnTriggerExit2D(Collider2D collision)
	{
		if (collision.gameObject.tag == "Player")
			spaceToDig = false;

	}
}

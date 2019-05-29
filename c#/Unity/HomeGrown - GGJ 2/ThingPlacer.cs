using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ThingPlacer : MonoBehaviour
{
	public bool placeMode = true;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void FixedUpdate()
    {
		if (placeMode) {
			RaycastHit hit;
			Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
			if (Physics.Raycast(ray, out hit))
			{
				transform.position = new Vector3(hit.point.x,hit.point.y,0);

			}
			if (Input.GetMouseButtonDown(0))
			{
				placeMode = false;
				print("dropping");
			}
		}
	}

	private void OnMouseDown()
	{
		placeMode = !placeMode;
		print("toggleing place mode");
	}
}
